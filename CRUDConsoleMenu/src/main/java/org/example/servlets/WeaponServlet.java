package org.example.servlets;

import org.example.WeaponCrud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WeaponServlet extends HttpServlet {
    private final WeaponCrud weaponCrud;

    public WeaponServlet(WeaponCrud weaponCrud) {
        this.weaponCrud = weaponCrud;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        // Получаем параметры из запроса
        String weaponName = request.getParameter("weaponName");

        // Вызываем метод создания оружия
        weaponCrud.createWeapon(weaponName);

        // Возвращаем ответ клиенту
        out.println("Оружие успешно создано.");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        // Получаем параметры из запроса
        int weaponId = Integer.parseInt(request.getParameter("weaponId"));
        String newWeaponName = request.getParameter("newWeaponName");

        // Вызываем метод обновления оружия
        weaponCrud.updateWeapon(weaponId, newWeaponName);

        // Возвращаем ответ клиенту
        out.println("Оружие успешно обновлено.");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        // Получаем параметры из запроса
        int weaponId = Integer.parseInt(request.getParameter("weaponId"));

        // Вызываем метод удаления оружия
        weaponCrud.deleteWeapon(weaponId);

        // Возвращаем ответ клиенту
        out.println("Оружие успешно удалено.");
    }
}
