package org.example.servlets;

import org.example.LocationCrud;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/location")
public class LocationServlet extends HttpServlet {
    private final LocationCrud locationCrud;

    public LocationServlet(LocationCrud locationCrud) {
        this.locationCrud = locationCrud;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        String locationName = request.getParameter("locationName");

        locationCrud.createLocation(locationName);

        out.println("Локация успешно создана.");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        int locationId = Integer.parseInt(request.getParameter("locationId"));
        String newLocationName = request.getParameter("newLocationName");

        locationCrud.updateLocation(locationId, newLocationName);

        out.println("Локация успешно обновлена.");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        int locationId = Integer.parseInt(request.getParameter("locationId"));

        locationCrud.deleteLocation(locationId);

        out.println("Локация успешно удалена.");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        int locationId = Integer.parseInt(request.getParameter("locationId"));

        String locationName = locationCrud.getLocationNameById(locationId);

        if (locationName != null) {
            out.println("ID: " + locationId);
            out.println("LocationName: " + locationName);
        } else {
            out.println("Локация не найдена.");
        }
    }
}
