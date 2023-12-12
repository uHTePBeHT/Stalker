package org.example.servlets;

import org.example.RankCrud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RankServlet extends HttpServlet {
    private final RankCrud rankCrud;

    public RankServlet(RankCrud rankCrud) {
        this.rankCrud = rankCrud;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        // Получаем параметры из запроса
        String rankName = request.getParameter("rankName");

        // Вызываем метод создания ранга
        rankCrud.createRank(rankName);

        // Возвращаем ответ клиенту
        out.println("Ранг успешно создан.");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        // Получаем параметры из запроса
        int rankId = Integer.parseInt(request.getParameter("rankId"));
        String newRankName = request.getParameter("newRankName");

        // Вызываем метод обновления ранга
        rankCrud.updateRank(rankId, newRankName);

        // Возвращаем ответ клиенту
        out.println("Ранг успешно обновлен.");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        // Получаем параметры из запроса
        int rankId = Integer.parseInt(request.getParameter("rankId"));

        // Вызываем метод удаления ранга
        rankCrud.deleteRank(rankId);

        // Возвращаем ответ клиенту
        out.println("Ранг успешно удален.");
    }
}
