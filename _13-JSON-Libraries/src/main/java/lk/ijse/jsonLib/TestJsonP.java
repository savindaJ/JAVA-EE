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
import jakarta.json.*;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 * @author : savindaJ
 * @date : 2024-01-05
 * @since : 0.1.0
 **/
@WebServlet(urlPatterns = "/lib")
public class TestJsonP extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        JsonArrayBuilder list = Json.createArrayBuilder();

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
            resp.getWriter().println("<h1>Error !!!</h1>");
            throw new RuntimeException(e);
        }

        /*JsonObject jsonObject = Json.createReader(req.getReader()).readObject();

        System.out.println(jsonObject.getString("id"));
        System.out.println(jsonObject.getString("name"));
        JsonObject address = jsonObject.getJsonObject("address");
        System.out.println(address.getInt("street"));
        System.out.println(address.getString("addr"));

        JsonArray contacts = jsonObject.getJsonArray("contacts");


        System.out.println(contacts.stream().takeWhile(jsonValue -> false));

        //get Json array values ! in string type !
        String num1 = contacts.getString(0);
        String num2 = contacts.getString(1);

        System.out.println(num1);
        System.out.println(num2);

        resp.getWriter().write("{state:\"ok\"}");*/

        // can send a json object by using create object builder


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//        JsonObject jsonObject = Json.createReader(req.getReader()).readObject();
        Jsonb builder = JsonbBuilder.create();
        Customer my = builder.fromJson(req.getReader(), Customer.class);

        String id = my.getId();
        String name = my.getName();
        String address = my.getAddress();
        Double salary = my.getSalary();

        if (id.length()>3){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"invalid customer ID !");
            return;
        }


        /*JsonReader reader = Json.createReader(req.getReader());
        JsonObject reqObj = reader.readObject();
        String id = reqObj.getString("id");
        String name = reqObj.getString("name");
        String address = reqObj.getString("address");
        String salary = reqObj.getString("salary");*/
//        JsonArray items = reqObj.getJsonArray("items");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection root = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/web_test", "root", "80221474");
            PreparedStatement pstm = root.prepareStatement("INSERT INTO customer VALUES (?,?,?,?)");
            pstm.setString(1, id);
            pstm.setString(2, name);
            pstm.setString(3, address);
            pstm.setDouble(4, salary);
            if (pstm.executeUpdate() > 0) {
                JsonObjectBuilder json = Json.createObjectBuilder();
                json.add("State", "OK");
                json.add("Message", "Successfully Saved !");
                resp.setContentType("application/json");
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write(json.build().toString());
            } else {
                JsonObjectBuilder json = Json.createObjectBuilder();
                json.add("State", "NOT");
                json.add("Message", "Not Saved !");
                resp.setContentType("application/json");
                resp.getWriter().write(json.build().toString());
            }
        } catch (Exception e) {
            JsonObjectBuilder json = Json.createObjectBuilder();
            json.add("State", "NOT");
            json.add("Message", e.getLocalizedMessage());
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            resp.getWriter().write(json.build().toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*var reader = Json.createReader(req.getReader());
        JsonObject reqObj = reader.readObject();
        String id = reqObj.getString("id");
        String name = reqObj.getString("name");
        String address = reqObj.getString("address");
        String salary = reqObj.getString("salary");*/

//        JsonObject jsonObject = Json.createReader(req.getReader()).readObject();
        Jsonb builder = JsonbBuilder.create();
        Customer my = builder.fromJson(req.getReader(), Customer.class);

        String id = my.getId();
        String name = my.getName();
        String address = my.getAddress();
        Double salary = my.getSalary();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection root = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/web_test", "root", "80221474");
            PreparedStatement pstm = root.prepareStatement("UPDATE customer SET name=? ,address=? ,salary=? WHERE cus_id=?");
            pstm.setString(1, name);
            pstm.setString(2, address);
            pstm.setDouble(3, salary);
            pstm.setString(4, id);
            if (pstm.executeUpdate() > 0) {
                var json = Json.createObjectBuilder();
                json.add("State", "OK");
                json.add("Message", "Successfully Updated !");
                resp.setContentType("application/json");
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT); //204
                resp.getWriter().write(json.build().toString());
            } else {
                var json = Json.createObjectBuilder();
                json.add("State", "NOT");
                json.add("Message", "Not Update !");
                resp.setContentType("application/json");
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); //500
                resp.getWriter().write(json.build().toString());
            }
        } catch (Exception e) {
            var json = Json.createObjectBuilder();
            json.add("State", "NOT");
            json.add("Message", e.getLocalizedMessage());
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED); //417
            resp.getWriter().write(json.build().toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*var reader = Json.createReader(req.getReader());
        var reqObj = reader.readObject();
        String id = reqObj.getString("id");*/

//        JsonObject jsonObject = Json.createReader(req.getReader()).readObject();
        Jsonb builder = JsonbBuilder.create();
        Customer my = builder.fromJson(req.getReader(), Customer.class);

        String id = my.getId();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection root = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/web_test", "root", "80221474");
            PreparedStatement pstm = root.prepareStatement("DELETE FROM customer WHERE cus_id=?");
            pstm.setString(1, id);
            if (pstm.executeUpdate() > 0) {
                JsonObjectBuilder json = Json.createObjectBuilder();
                json.add("State", "OK");
                json.add("Message", "Successfully Deleted !");
                resp.setContentType("application/json");
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204
                resp.getWriter().write(json.build().toString());
            } else {
                JsonObjectBuilder json = Json.createObjectBuilder();
                json.add("State", "NOT");
                json.add("Message", "Not Delete !");
                resp.setContentType("application/json");
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Faild to delete this customer !");
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write(json.build().toString());
            }
        } catch (Exception e) {
            JsonObjectBuilder json = Json.createObjectBuilder();
            json.add("State", "NOT");
            json.add("Message", e.getLocalizedMessage());
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            resp.getWriter().write(json.build().toString());
            throw new RuntimeException(e);
        }
    }
}
