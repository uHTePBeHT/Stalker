package org.example.servlets;

import org.example.GroupDataCrud;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/groupData")
public class GroupDataServlet extends HttpServlet {
    private final GroupDataCrud groupDataCrud;

    public GroupDataServlet(GroupDataCrud groupDataCrud) {
        this.groupDataCrud = groupDataCrud;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        String groupName = request.getParameter("groupName");

        groupDataCrud.createGroup(groupName);

        out.println("Группировка успешно создана.");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        int groupId = Integer.parseInt(request.getParameter("groupId"));
        String newGroupName = request.getParameter("newGroupName");

        groupDataCrud.updateGroup(groupId, newGroupName);

        out.println("Группировка успешно обновлена.");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        int groupId = Integer.parseInt(request.getParameter("groupId"));

        groupDataCrud.deleteGroup(groupId);

        out.println("Группировка успешно удалена.");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        int groupId = Integer.parseInt(request.getParameter("groupId"));

        // Вызываем метод получения группы по ID
        String groupName = groupDataCrud.getGroupNameById(groupId);

        if (groupName != null) {
            out.println("ID: " + groupId);
            out.println("GroupName: " + groupName);
        } else {
            out.println("Группа не найдена.");
        }
    }
}
