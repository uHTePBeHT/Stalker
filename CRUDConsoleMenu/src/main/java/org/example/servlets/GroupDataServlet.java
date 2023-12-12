package org.example.servlets;

import org.example.GroupDataCrud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GroupDataServlet extends HttpServlet {
    private final GroupDataCrud groupDataCrud;

    public GroupDataServlet(GroupDataCrud groupDataCrud) {
        this.groupDataCrud = groupDataCrud;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        // Получаем параметры из запроса
        String groupName = request.getParameter("groupName");

        // Вызываем метод создания группировки
        groupDataCrud.createGroup(groupName);

        // Возвращаем ответ клиенту
        out.println("Группировка успешно создана.");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        // Получаем параметры из запроса
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        String newGroupName = request.getParameter("newGroupName");

        // Вызываем метод обновления группировки
        groupDataCrud.updateGroup(groupId, newGroupName);

        // Возвращаем ответ клиенту
        out.println("Группировка успешно обновлена.");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        // Получаем параметры из запроса
        int groupId = Integer.parseInt(request.getParameter("groupId"));

        // Вызываем метод удаления группировки
        groupDataCrud.deleteGroup(groupId);

        // Возвращаем ответ клиенту
        out.println("Группировка успешно удалена.");
    }
}
