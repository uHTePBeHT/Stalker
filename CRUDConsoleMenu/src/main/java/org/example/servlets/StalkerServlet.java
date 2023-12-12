package org.example.servlets;

import org.example.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/stalker")
public class StalkerServlet extends HttpServlet {
    private final StalkerCrud stalkerCrud;

    public StalkerServlet(StalkerCrud stalkerCrud) {
        this.stalkerCrud = stalkerCrud;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        int rankId = Integer.parseInt(request.getParameter("rankId"));
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        int suitId = Integer.parseInt(request.getParameter("suitId"));
        int weaponId = Integer.parseInt(request.getParameter("weaponId"));
        int money = Integer.parseInt(request.getParameter("money"));

        stalkerCrud.createStalker(firstName, secondName, groupId, rankId, locationId, suitId, weaponId, money);

        out.println("Сталкер успешно создан.");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        int stalkerId = Integer.parseInt(request.getParameter("stalkerId"));
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        int rankId = Integer.parseInt(request.getParameter("rankId"));
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        int suitId = Integer.parseInt(request.getParameter("suitId"));
        int weaponId = Integer.parseInt(request.getParameter("weaponId"));
        int money = Integer.parseInt(request.getParameter("money"));

        stalkerCrud.updateStalker(stalkerId, firstName, secondName, groupId, rankId, locationId, suitId, weaponId, money);

        out.println("Сталкер успешно обновлен.");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        int stalkerId = Integer.parseInt(request.getParameter("stalkerId"));

        stalkerCrud.deleteStalker(stalkerId);

        out.println("Сталкер успешно удален.");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        int stalkerId = Integer.parseInt(request.getParameter("stalkerId"));

        // Вызываем метод получения сталкера по ID
        Stalker stalker = stalkerCrud.getStalkerById(stalkerId);

        if (stalker != null) {
            out.println("ID: " + stalker.getId());
            out.println("FirstName: " + stalker.getFirstName());
            out.println("SecondName: " + stalker.getSecondName());
            out.println("Location: " + stalker.getLocation());
            out.println("Suit: " + stalker.getSuit());
            out.println("Weapon: " + stalker.getWeapon());
            out.println("Money: " + stalker.getMoney());
            out.println("Rank: " + stalker.getRank());
            out.println("Group: " + stalker.getGroup());
        } else {
            out.println("Сталкер не найден.");
        }
    }
}
