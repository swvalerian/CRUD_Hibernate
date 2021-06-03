package com.swvalerian.crud;

import com.swvalerian.crud.model.Developer;
import com.swvalerian.crud.model.Skill;
import com.swvalerian.crud.repository.hibernate.HibernateDeveloperRepositoryImpl;
import com.swvalerian.crud.repository.hibernate.HibernateSkillRepositoryImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppRunner {
    public static void main(String[] args) throws SQLException {

        HibernateDeveloperRepositoryImpl HDR = new HibernateDeveloperRepositoryImpl();
        System.out.printf(HDR.getAll().toString());
        System.out.println("=====================!!!!!!!!!!!!!!!!!! Вывод всех !!!!!!!!!!!!!!===============");
        Developer developer = HDR.getId(3l);
        System.out.printf("\n" + "Данные о девелопере под номером = 3: " + developer.toString());

        // далее проверим метод Save
        List<Skill> skills = new ArrayList<>();
        // для того, чтоб создать нового девелопера, нужно создать список skills
        // позаимствуем умения у предыдущего девелопера
        skills.addAll(developer.getSkills());

        System.out.println("\n" + "после этой строки идет запрос на сохранение в базу" + "\n");
        Developer developerSave = new Developer(10,"Aleks","Kopicin", skills);
        HDR.save(developerSave); // сохраним нового девелопера, ай-ди ставится автоматом инкремент

        System.out.println("\n"+"============= Выведем списко всех, где увидим и нового Developer'a =============" + "\n");
        System.out.printf(HDR.getAll().toString());

        System.out.printf("\n" + "=========== Далее изменим данные у девелопера под номером 7 ==========" + "\n");
        // сформируем список умений другим способом, заодно потестим класс HibernateSkillRepositoryImpl
        HibernateSkillRepositoryImpl HSR = new HibernateSkillRepositoryImpl();
        skills = new ArrayList<>();

        HSR.save(new Skill(8,"Fly"));
        HSR.save(new Skill(10, "Drink"));

        skills.add(HSR.getId(4));
        skills.add(HSR.getId(2));
        skills.add(HSR.getId(8));
        skills.add(HSR.getId(7));

        Developer developerUpdate = new Developer(7, "Vasiliy","Alibaba", skills);
        HDR.update(developerUpdate);
        System.out.println("\n" + "Обновленный девпелопер под номером = 7 должен стать таким:" + developerUpdate.toString() + "\n");
        System.out.println("\n" + "Данные о девелопере под номером = 7 после Update: " + HDR.getId(7l) + "\n");

        // теперь удалим девелопера!
        System.out.println("===================== Удалим девелопера под номером 4 и выведем список с получившимися изменениями ==========================");

        HDR.deleteById(3l);

        System.out.printf(HDR.getAll().toString()); // выведем список с отсутствующим девелопером

        // Тестирование класса HibernateSkillRepositoryImpl из слоя РЕПО - успешно законечно!
        /*HibernateSkillRepositoryImpl HSR = new HibernateSkillRepositoryImpl();
        System.out.println("\n" + "Начальный вид таблицы Skill"  + "\n" + HSR.getAll());

        Skill skillUpdate = new Skill(6,"JS");
        HSR.update(skillUpdate);

        HSR.save(new Skill(7,"7lala7"));

        System.out.println("\n" + "Добавили элемент и изменили элемент под номером 6"  + "\n" + HSR.getAll());

        // после каждого запуска тут нужно увеличивать число - иначе будет эксепшн - удаление несуществующего элемента
        HSR.deleteById(17);

        System.out.println("\n" + "Удалили элимент из таблицы Skill"  + "\n" + HSR.getAll());

        System.out.println("-------------=========================--------------------");

        skillUpdate = new Skill(6,"Basic");
        HSR.update(skillUpdate);

        System.out.println("\n" + "Update Skill"  + "\n" + HSR.getAll());*/

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
