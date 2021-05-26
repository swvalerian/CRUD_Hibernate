package com.swvalerian.crud.repository.jdbc;

import com.swvalerian.crud.model.Skill;
import com.swvalerian.crud.repository.SkillRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillRepository implements SkillRepo {
    static Connection connection = DatabaseConnection.getConnection();

    // приватный метод, создание списка из файла, который повторяется по коду много раз.
    private List<Skill> getListFromDB() {
        List<Skill> skillList = new ArrayList<>();
        // подготовили запрос
        String SQL = "SELECT * FROM Skills";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            // выполняем запрос
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                skillList.add(new Skill(result.getInt("ID"), result.getString("Skill")));
            }
            result.close();
        } catch (SQLException e) {
            System.err.println("Ошибка выполнения запроса в SkillRep.GetListfromDB");
        }
        return skillList;
    }

    public List<Skill> getAll() {
        return getListFromDB();
    }

    public Skill getId(Integer id) {
        return getListFromDB().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Skill> update(Skill skills) {
        Integer id = skills.getId();
        String skillName = skills.getName();
        // подготовим запрос на изменение таблицы
        String SQL = "UPDATE Skills SET Skill=? WHERE Id=?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, skillName); // вместо первого знака ?
            preparedStatement.setInt(2, id); // вместо второго знака ?
            // выполняем запрос
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка выполнения запроса в методе SkillRep.UPDATE");
        }
        return getListFromDB();
    }

    public Skill save(Skill skills) {
        String skillName = skills.getName();
        // подготовим запрос на добавление нового элемента в таблицу
        String SQL = "INSERT INTO Skills (Skill) values(?);"; // у нас автоинкремент, ID автоматом проставится
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, skillName);
            // выполняем запрос
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка выполнения запроса в методе SkillRep.SAVE");
        }
        return skills;
    }

    public void deleteById(Integer id) {
        // подготовим запрос для удаления записи из таблицы
        String SQL = "DELETE FROM Skills WHERE Id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            // выполняем запрос
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка выполнения запроса в методе SkillRep.DeleteByID");
        }
    }
}