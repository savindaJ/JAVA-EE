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

package lk.ijse.ajax.api;

import com.google.gson.Gson;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.DriverManager;
import java.util.ArrayList;

/**
 * @author : savindaJ
 * @date : 2024-01-12
 * @since : 0.1.0
 **/
@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            var root = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/web_test", "root", "80221474");
            var pstm = root.prepareStatement("SELECT * FROM customer");
            var set = pstm.executeQuery();
            while (set.next()) {

                customers.add(new Customer(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getDouble(4)
                ));
                //  using Json-P
                /*var jsonObjectBuilder = Json.createObjectBuilder()
                .add("id", set.getString(1))
                .add("name", set.getString(2))
                .add("address", set.getString(3))
                .add("salary", set.getDouble(4));*/
                //  list.add(jsonObjectBuilder.build());
            }
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().println(new Gson().toJson(customers));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        Customer customer = jsonb.fromJson(req.getReader(), Customer.class);
        System.out.println(customer);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            var root = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/web_test", "root", "80221474");
            var pstm = root.prepareStatement("INSERT INTO customer VALUES (?,?,?,?)");
            pstm.setString(1,customer.getId());
            pstm.setString(2,customer.getName());
            pstm.setString(3,customer.getAddress());
            pstm.setDouble(4,customer.getSalary());
            var set = pstm.executeUpdate();

            if(set>0){
                resp.setContentType("application/json");
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write(jsonb.toJson(new RespMessage("Successfully saved !","ok")));
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("put !");
        Jsonb jsonb = JsonbBuilder.create();
        Customer customer = jsonb.fromJson(req.getReader(), Customer.class);
        System.out.println(customer);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
