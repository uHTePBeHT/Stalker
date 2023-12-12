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

        // Получаем параметры из запроса
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        int rankId = Integer.parseInt(request.getParameter("rankId"));
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        int suitId = Integer.parseInt(request.getParameter("suitId"));
        int weaponId = Integer.parseInt(request.getParameter("weaponId"));
        int money = Integer.parseInt(request.getParameter("money"));

        // Вызываем метод создания сталкера
        stalkerCrud.createStalker(firstName, secondName, groupId, rankId, locationId, suitId, weaponId, money);

        // Возвращаем ответ клиенту
        out.println("Сталкер успешно создан.");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        // Получаем параметры из запроса
        int stalkerId = Integer.parseInt(request.getParameter("stalkerId"));
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        int rankId = Integer.parseInt(request.getParameter("rankId"));
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        int suitId = Integer.parseInt(request.getParameter("suitId"));
        int weaponId = Integer.parseInt(request.getParameter("weaponId"));
        int money = Integer.parseInt(request.getParameter("money"));

        // Вызываем метод обновления сталкера
        stalkerCrud.updateStalker(stalkerId, firstName, secondName, groupId, rankId, locationId, suitId, weaponId, money);

        // Возвращаем ответ клиенту
        out.println("Сталкер успешно обновлен.");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        // Получаем параметры из запроса
        int stalkerId = Integer.parseInt(request.getParameter("stalkerId"));

        // Вызываем метод удаления сталкера
        stalkerCrud.deleteStalker(stalkerId);

        // Возвращаем ответ клиенту
        out.println("Сталкер успешно удален.");
    }
}
