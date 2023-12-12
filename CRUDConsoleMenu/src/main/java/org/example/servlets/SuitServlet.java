package org.example.servlets;

import org.example.SuitCrud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SuitServlet extends HttpServlet {
    private final SuitCrud suitCrud;

    public SuitServlet(SuitCrud suitCrud) {
        this.suitCrud = suitCrud;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        // Получаем параметры из запроса
        String suitName = request.getParameter("suitName");

        // Вызываем метод создания костюма
        suitCrud.createSuit(suitName);

        // Возвращаем ответ клиенту
        out.println("Костюм успешно создан.");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        // Получаем параметры из запроса
        int suitId = Integer.parseInt(request.getParameter("suitId"));
        String newSuitName = request.getParameter("newSuitName");

        // Вызываем метод обновления костюма
        suitCrud.updateSuit(suitId, newSuitName);

        // Возвращаем ответ клиенту
        out.println("Костюм успешно обновлен.");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        // Получаем параметры из запроса
        int suitId = Integer.parseInt(request.getParameter("suitId"));

        // Вызываем метод удаления костюма
        suitCrud.deleteSuit(suitId);

        // Возвращаем ответ клиенту
        out.println("Костюм успешно удален.");
    }
}
