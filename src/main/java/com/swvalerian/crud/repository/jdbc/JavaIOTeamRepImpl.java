package com.swvalerian.crud.repository.jdbc;

import com.swvalerian.crud.model.Developer;
import com.swvalerian.crud.model.Team;
import com.swvalerian.crud.repository.TeamRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Team(id, name, List<Developer> developers)
public class JavaIOTeamRepImpl implements TeamRepository {
    static private final String DATABASE_URL = "jdbc:mysql://localhost:3306/swvalerian";
    static private final String User = "root";
    static private final String Password = "QWERTgfdsa1980";

    // JDBC!
    private List<Team> getListFFTeam() {
        List<Team> teamList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, User, Password)) {
            // подготовили запрос: достаточно получить ID участников, чтоб в дальнейшем составить их список
            String SQL = "select id, Name, Dev_id FROM Team\n" +
                        "JOIN Team_Developers\n" +
                        "ON Team.Id = Team_Developers.Team_Id;";

            try ( PreparedStatement preparedStatement = connection.prepareStatement(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                // выполняем запрос
                ResultSet rS = preparedStatement.executeQuery();
                int prevId = 1; // для сравнения ID, если team та же самая, тогда список Developers заполняем дальше

                while (rS.next()) {
                    // этот список постоянно будет изменяться, т.е. создавать надо заново, поэтому обьявление списка именно тут
                    List<Developer> devList = new ArrayList<>();
                    int id = rS.getInt("id");
                    String name = rS.getString("Name");

                    while (id == prevId) {
                        long Dev_id = rS.getLong("Dev_id");
                        devList.add(new JavaIODevRepImpl().getId(Dev_id));
                        if (rS.next()) {
                            id = rS.getInt("Id");
                        } else {
                            break;
                        }
                    }
                    teamList.add(new Team(prevId, name, devList));
                    prevId = id; // изменили значение на следующее.
                    // теперь нужно вернуть указатель на предыдущий маркер, т.к. при следующей итерации он вновь сдвинется
                    rS.previous();
                }
                rS.close();
            }
        } catch (SQLException e) {
            System.err.println("Ошибка в работе с БД в ДевРеп -> метод getListFFD");
        }
        return teamList;
    }

    // JDBC!
    @Override
    public List<Team> getAll() {
        return getListFFTeam();
    }

    // JDBC!
    @Override
    public Team getId(Long id) {
        return getListFFTeam().stream().filter( s -> s.getId().equals(id.intValue())).findFirst().orElse(null);
    }

    // JDBC!
    @Override
    public List<Team> update(Team team) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, User, Password)){
            // подготовим запрос для изменения записи в таблице
            String SQL = "UPDATE Team SET Name=? WHERE Id = ?;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
                preparedStatement.setString(1, team.getName());
                preparedStatement.setInt(2, team.getId());
                // выполняем запрос
                preparedStatement.executeUpdate();
            }

            // теперь нам надо изменить данные о девелоперах в команде - тут по хитрому поступим.
            // Сначало сотрем все старые данные об умениях (и неважно где они в таблице)
            // а после добавим новые данные, в конец таблицы, тоже не важно - где они хранятся.
            SQL = "DELETE FROM Team_Developers WHERE Team_id = ?;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
                preparedStatement.setLong(1, team.getId());
                // удаляем
                preparedStatement.executeUpdate();
            }

            //а теперь добавим данные о сотаве команды в таблицу Team_Developers
            List<Developer> developerList = team.getDevelopers(); // получим список

            SQL = "INSERT INTO Team_Developers (Team_Id, Dev_Id) values(?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
                for (int i = 0; i < developerList.size(); i++) {
                    preparedStatement.setInt(1, team.getId());
                    preparedStatement.setInt(2, developerList.get(i).getId());
                    preparedStatement.addBatch(); // пишем запросы в пакет
                }
                preparedStatement.executeBatch(); // пакетный запуск запросов.
            }
        } catch (SQLException e) {
            System.err.println("Ошибка работы с БД в методе TeamRep.UpDate");
        }
        return getListFFTeam();
    }

    // JDBC!
    private void writeBuf(Team team) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, User, Password)) {
            String name = team.getName();
            List<Developer> developerList = team.getDevelopers();
            int Team_Id; // понадобится для корректного получения ID

            // подготовим запрос на добавление нового элемента в таблицу
            String SQL = "INSERT INTO Team (Name) values(?);"; // у нас автоинкремент, ID автоматом проставится
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
                preparedStatement.setString(1, name);
                preparedStatement.executeUpdate();

                SQL = "SELECT last_insert_id();"; // надо узнать номер инкремента, чтоб корректно добавить ID в Team_Dev
                ResultSet resultSet = preparedStatement.executeQuery(SQL);
                resultSet.next();
                Team_Id  = resultSet.getInt(1); // получили ID вновь добавленной команды
            }

            // теперь запишем developers в таблицу Team_developers
            SQL = "INSERT INTO Team_Developers (Team_Id, Dev_Id) values(?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)){

                for (int i = 0; i < developerList.size(); i++) {
                    preparedStatement.setInt(1, Team_Id);
                    preparedStatement.setInt(2, developerList.get(i).getId());
                    preparedStatement.addBatch(); // пишем запросы в пакет
                }
                preparedStatement.executeBatch(); // пакетный запуск запросов.
            }
        } catch (SQLException e) {
            System.err.println("Ошибка работы с БД в методе TeamRep.WriteBuf");
        }
    }

    // JDBC!
    @Override
    public Team save(Team team) {
        writeBuf(team);
        return team;
    }

    // JDBC!
    @Override
    public void deleteById(Long id) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, User, Password)){
            // подготовим запрос для удаления записи из таблицы
            String SQL = "DELETE FROM Team WHERE Id = ?;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
                preparedStatement.setLong(1, id);
                // выполняем запрос
                preparedStatement.executeUpdate();

                // подготовим запрос для удаления записей из таблицы Team_Developers
                SQL = "DELETE FROM Team_Developers WHERE Team_id = ?;";
            }
             try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
                preparedStatement.setLong(1, id);
                // выполняем запрос
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Ошибка работы с БД в методе Team.DeleteByID");
        }
    }
}