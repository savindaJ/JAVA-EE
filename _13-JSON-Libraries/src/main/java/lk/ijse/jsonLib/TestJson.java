/*
 * Copyright (c) 2024.
 * All Rights Reserved
 *  * You may use, distribute and modify this code under the
 *  * terms of the XYZ license, which unfortunately won't be
 *  * written for another century.
 *  *
 *  * You should have received a copy of the XYZ license with
 *  * this file. If not, please write to: , or visit :
 */

package lk.ijse.jsonLib;

import com.google.gson.Gson;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author : savindaJ
 * @date : 2024-01-05
 * @since : 0.1.0
 **/
@WebServlet(urlPatterns = "/lib")
public class TestJson extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        JsonArrayBuilder list = Json.createArrayBuilder();

        ArrayList<Customer> customers = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection root = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/web_test", "root", "80221474");
            PreparedStatement pstm = root.prepareStatement("SELECT * FROM customer");
            ResultSet set = pstm.executeQuery();
            while (set.next()) {

                customers.add(new Customer(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getDouble(4)
                ));

                /*JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
                jsonObjectBuilder.add("id",set.getString(1));
                jsonObjectBuilder.add("name",set.getString(2));
                jsonObjectBuilder.add("address",set.getString(3));
                jsonObjectBuilder.add("salary",set.getDouble(4));
                list.add(jsonObjectBuilder.build());*/
            }
            resp.setContentType("application/json");
            resp.getWriter().println(new Gson().toJson(customers));
        } catch (Exception e) {
            resp.getWriter().println("<h1>Error !!!</h1>");
            throw new RuntimeException(e);
        }
    }
}
