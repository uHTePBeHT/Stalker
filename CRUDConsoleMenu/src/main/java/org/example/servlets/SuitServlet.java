package org.example.servlets;

import org.example.SuitCrud;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/suit")
public class SuitServlet extends HttpServlet {
    private final SuitCrud suitCrud;

    public SuitServlet(SuitCrud suitCrud) {
        this.suitCrud = suitCrud;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        String suitName = request.getParameter("suitName");

        suitCrud.createSuit(suitName);

        out.println("Костюм успешно создан.");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        int suitId = Integer.parseInt(request.getParameter("suitId"));
        String newSuitName = request.getParameter("newSuitName");

        suitCrud.updateSuit(suitId, newSuitName);

        out.println("Костюм успешно обновлен.");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        int suitId = Integer.parseInt(request.getParameter("suitId"));

        suitCrud.deleteSuit(suitId);

        out.println("Костюм успешно удален.");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        int suitId = Integer.parseInt(request.getParameter("suitId"));

        String suitName = suitCrud.getSuitNameById(suitId);

        if (suitName != null) {
            out.println("ID: " + suitId);
            out.println("SuitName: " + suitName);
        } else {
            out.println("Костюм не найден.");
        }
    }
}
