package com.swvalerian.crud;

import com.swvalerian.crud.model.Skill;
import com.swvalerian.crud.repository.SkillRepo;
import com.swvalerian.crud.repository.hibernate.HibernateSkillRepositoryImpl;
import com.swvalerian.crud.repository.jdbc.SkillRepository;

import java.sql.SQLException;

public class AppRunner {
    public static void main(String[] args) throws SQLException {

        HibernateSkillRepositoryImpl HSR = new HibernateSkillRepositoryImpl();
        System.out.println("\n" + "Начальный вид таблицы Skill"  + "\n" + HSR.getAll());

        Skill skillUpdate = new Skill(6,"JS");
        HSR.update(skillUpdate);

        HSR.save(new Skill(7,"7lala7"));

        System.out.println("\n" + "Добавили элемент и изменили элемент под номером 6"  + "\n" + HSR.getAll());

        // после каждого запуска тут нужно увеличивать число - иначе будет эксепшн - удаление несуществующего элемента
        HSR.deleteById(15);

        System.out.println("\n" + "Удалили элимент из таблицы Skill"  + "\n" + HSR.getAll());

        System.out.println("-------------=========================--------------------");

        skillUpdate = new Skill(6,"Basic");
        HSR.update(skillUpdate);

        System.out.println("\n" + "Update Skill"  + "\n" + HSR.getAll());

        /*JavaIOTeamRepImpl teamRep = new JavaIOTeamRepImpl();

        List<Team> teamList = teamRep.getAll();

        // test1
        System.out.println("\nПроверка метода getAll");
        for (Team team:teamList) {
            System.out.println(team + "\n");
        }

        // test2
        System.out.println("\nПроверим метод getId = 1 -> " + teamRep.getId(1l));*/

        // test3
//        System.out.println("\nПроверим метод save");
//        List<Developer> developerList = new JavaIODevRepImpl().getAll();
//        developerList.remove(1);
//        developerList.remove(5);
//        Team team = new Team(10,"TryCatch", developerList);// неважно каким будет ID
//        teamRep.save(team);

//      проверка метода удаления записи
//      teamRep.deleteById(13l);

        // тест DevRepo
//        DeveloperRepository devRep = new JavaIODevRepImpl();
//        System.out.println(devRep.getAll());
//        System.out.println(devRep.getId(20L));
//
//        SkillRepository skillRepository = new SkillRepository();
//        List<Skill> skillList = new ArrayList<>();
//        skillList.add(skillRepository.getById(20));
//
//        devRep.update(new Developer(17,"Pasha", "Sorokin", skillList)); // работает
//        System.out.println(devRep.getId(17l)); // работает
//
//        // devRep.save(new Developer(25, "Danil", "Markov", skillList)); // работает, неважно что в ID - т.к. автоинкремент! и я это учел!
//        devRep.deleteById(19l); // работает

        // тест SkillRepo
        /*SkillRepository skillRepository = new SkillRepository();
        List<Skill> sl = skillRepository.getAll();
        System.out.println(sl.get(1));
        System.out.println("\n " + skillRepository.getById(1));
        skillRepository.update(new Skill(1, "Java EE"));
        skillRepository.save(new Skill(21, "Basic")); // неважно какой указать, автоинкремент
        skillRepository.deleteById(20);*/
    }
}
