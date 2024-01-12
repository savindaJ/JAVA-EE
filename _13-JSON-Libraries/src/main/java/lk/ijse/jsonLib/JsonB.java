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

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : savindaJ
 * @date : 2024-01-12
 * @since : 0.1.0
 **/
@WebServlet(urlPatterns = "/jsonb")
public class JsonB extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* Customer customer = new Customer("C001", "savinda", "matara", 125000.3);
        Customer customer2 = new Customer("C002", "kamal", "galle", 13500.6);

        ArrayList<Customer> list = new ArrayList<>();

        list.add(customer);
        list.add(customer2);

        Jsonb jsonb = JsonbBuilder.create();
        // convert and send response !
        jsonb.toJson(list,resp.getWriter());*/




        Jsonb builder = JsonbBuilder.create();

        Customer customer1 = new Customer("C003", "Deshan", "matara", 3456889.1);
        String json = builder.toJson(customer1);
        Customer customer3 = builder.fromJson(json, Customer.class);
        System.out.println(customer3);
    }
}
