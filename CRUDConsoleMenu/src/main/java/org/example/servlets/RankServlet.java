package org.example.servlets;

import org.example.RankCrud;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/rank")
public class RankServlet extends HttpServlet {
    private final RankCrud rankCrud;

    public RankServlet(RankCrud rankCrud) {
        this.rankCrud = rankCrud;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        String rankName = request.getParameter("rankName");

        rankCrud.createRank(rankName);

        out.println("Ранг успешно создан.");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        int rankId = Integer.parseInt(request.getParameter("rankId"));
        String newRankName = request.getParameter("newRankName");

        rankCrud.updateRank(rankId, newRankName);

        out.println("Ранг успешно обновлен.");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        int rankId = Integer.parseInt(request.getParameter("rankId"));

        rankCrud.deleteRank(rankId);

        out.println("Ранг успешно удален.");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        int rankId = Integer.parseInt(request.getParameter("rankId"));

        String rankName = rankCrud.getRankNameById(rankId);

        if (rankName != null) {
            out.println("ID: " + rankId);
            out.println("RankName: " + rankName);
        } else {
            out.println("Ранг не найден.");
        }
    }
}
