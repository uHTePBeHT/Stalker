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
    public static void main(String[] args) throws LifecycleException, SQLException {

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        Context ctx = tomcat.addContext("", null);

        Wrapper groupDataS = Tomcat.addServlet(ctx, "groupDataServlet", new GroupDataServlet());
        groupDataS.setLoadOnStartup(1);
        groupDataS.addMapping("/group/*");

        Wrapper rankS = Tomcat.addServlet(ctx, "rankServlet", new RankServlet());
        rankS.setLoadOnStartup(1);
        rankS.addMapping("/rank/*");

        Wrapper locationS = Tomcat.addServlet(ctx, "locationServlet", new LocationServlet());
        locationS.setLoadOnStartup(1);
        locationS.addMapping("/location/*");

        Wrapper suitS = Tomcat.addServlet(ctx, "suitServlet", new SuitServlet());
        suitS.setLoadOnStartup(1);
        suitS.addMapping("/suit/*");

        Wrapper weaponS = Tomcat.addServlet(ctx, "weaponServlet", new WeaponServlet());
        weaponS.setLoadOnStartup(1);
        weaponS.addMapping("/weapon/*");

        Wrapper stalkerS = Tomcat.addServlet(ctx, "stalkerServlet", new StalkerServlet());
        stalkerS.setLoadOnStartup(1);
        stalkerS.addMapping("/stalker/*");

        tomcat.start();

        /*try {
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
        }*/
    }
}
