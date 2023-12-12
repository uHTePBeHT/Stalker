package org.example.servlets;

import org.example.LocationCrud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LocationServlet extends HttpServlet {
    private final LocationCrud locationCrud;

    public LocationServlet(LocationCrud locationCrud) {
        this.locationCrud = locationCrud;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        // Получаем параметры из запроса
        String locationName = request.getParameter("locationName");

        // Вызываем метод создания локации
        locationCrud.createLocation(locationName);

        // Возвращаем ответ клиенту
        out.println("Локация успешно создана.");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        // Получаем параметры из запроса
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        String newLocationName = request.getParameter("newLocationName");

        // Вызываем метод обновления локации
        locationCrud.updateLocation(locationId, newLocationName);

        // Возвращаем ответ клиенту
        out.println("Локация успешно обновлена.");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        // Получаем параметры из запроса
        int locationId = Integer.parseInt(request.getParameter("locationId"));

        // Вызываем метод удаления локации
        locationCrud.deleteLocation(locationId);

        // Возвращаем ответ клиенту
        out.println("Локация успешно удалена.");
    }
}
