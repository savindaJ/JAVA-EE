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

package lk.ijse.json;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author : savindaJ
 * @date : 2024-01-05
 * @since : 0.1.0
 **/
@WebServlet(urlPatterns = "/test")
public class Test extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<String> strings = new ArrayList<>();

        ArrayList<Customer> list = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection root = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/web_test", "root", "80221474");
            PreparedStatement pstm = root.prepareStatement("SELECT * FROM customer");
            ResultSet set = pstm.executeQuery();

            while (set.next()) {

                list.add(new Customer(set.getString(1),set.getString(2),set.getString(3),set.getDouble(4)));


                String start = "{";

                /*System.out.println("ID :" + set.getString(1) + "," +
                        "NAME :" + set.getString(2) + "," +
                        "Address :" + set.getString(3) + "," +
                        "Salary :" + set.getDouble(4)
                );*/

                String body = "\"id\"" + ":" +"\""+ set.getString(1)+"\"" + "," + "\"name\"" + ":" + "\""+ set.getString(2)+"\"" + "," +
                        "\"address\" :" +"\""+ set.getString(3) +"\""+ ","+"\"salary\" :"+"\""+set.getDouble(4);

                String end = "}";

                String json = start+body+end;

                strings.add(json);
            }
            resp.setContentType("application/json");

            resp.getWriter().println(new Gson().toJson(list));

            resp.getWriter().println(strings);


        } catch (Exception e) {
            resp.getWriter().println("<h1>Error !!!</h1>");
            throw new RuntimeException(e);
        }
    }
}
