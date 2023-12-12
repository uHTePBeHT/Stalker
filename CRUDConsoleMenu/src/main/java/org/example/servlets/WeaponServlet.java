package org.example.servlets;

import org.example.WeaponCrud;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/weapon")
public class WeaponServlet extends HttpServlet {
    private final WeaponCrud weaponCrud;

    public WeaponServlet(WeaponCrud weaponCrud) {
        this.weaponCrud = weaponCrud;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        String weaponName = request.getParameter("weaponName");

        weaponCrud.createWeapon(weaponName);

        out.println("Оружие успешно создано.");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        int weaponId = Integer.parseInt(request.getParameter("weaponId"));
        String newWeaponName = request.getParameter("newWeaponName");

        weaponCrud.updateWeapon(weaponId, newWeaponName);

        out.println("Оружие успешно обновлено.");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        int weaponId = Integer.parseInt(request.getParameter("weaponId"));

        weaponCrud.deleteWeapon(weaponId);

        out.println("Оружие успешно удалено.");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        int weaponId = Integer.parseInt(request.getParameter("weaponId"));

        String weaponName = weaponCrud.getWeaponNameById(weaponId);

        if (weaponName != null) {
            out.println("ID: " + weaponId);
            out.println("WeaponName: " + weaponName);
        } else {
            out.println("Оружие не найдено.");
        }
    }
}
