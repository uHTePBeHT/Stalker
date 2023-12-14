package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.Wrapper;
import org.example.servlets.*;
import javax.servlet.http.HttpServlet;
import java.sql.SQLException;
import org.example.servlets.GroupDataServlet;

public class MainApp {
    public static void main(String[] args) {

        try {
            Tomcat tomcat = new Tomcat();

            tomcat.setPort(8080);

            Context context = tomcat.addContext("/", System.getProperty("java.io.tmpdir"));

            // Создаем экземпляры классов для работы с базой данных
            DatabaseManager databaseManager = new DatabaseManager("jdbc:postgresql://localhost:5432/stalkers_db", "postgres", "postgres");
            GroupDataCrud groupDataCrud = new GroupDataCrud(databaseManager.getConnection());
            RankCrud rankCrud = new RankCrud(databaseManager.getConnection());
            LocationCrud locationCrud = new LocationCrud(databaseManager.getConnection());
            SuitCrud suitCrud = new SuitCrud(databaseManager.getConnection());
            WeaponCrud weaponCrud = new WeaponCrud(databaseManager.getConnection());
            StalkerCrud stalkerCrud = new StalkerCrud(databaseManager.getConnection(), groupDataCrud, rankCrud, locationCrud, suitCrud, weaponCrud);

            // Регистрируем сервлеты
            tomcat.addServlet("/", "GroupDataServlet", new GroupDataServlet(groupDataCrud)).setLoadOnStartup(1);
            tomcat.addServlet("/", "RankServlet", new RankServlet(rankCrud)).setLoadOnStartup(1);
            tomcat.addServlet("/", "LocationServlet", new LocationServlet(locationCrud)).setLoadOnStartup(1);
            tomcat.addServlet("/", "SuitServlet", new SuitServlet(suitCrud)).setLoadOnStartup(1);
            tomcat.addServlet("/", "WeaponServlet", new WeaponServlet(weaponCrud)).setLoadOnStartup(1);
            tomcat.addServlet("/", "StalkerServlet", new StalkerServlet(stalkerCrud)).setLoadOnStartup(1);

            context.addServletMappingDecoded("/groupData", "GroupDataServlet");
            context.addServletMappingDecoded("/rank", "RankServlet");
            context.addServletMappingDecoded("/location", "LocationServlet");
            context.addServletMappingDecoded("/suit", "SuitServlet");
            context.addServletMappingDecoded("/weapon", "WeaponServlet");
            context.addServletMappingDecoded("/stalker", "StalkerServlet");


            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
