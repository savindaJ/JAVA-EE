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

package lk.ijse.buildIn;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author : savindaJ
 * @date : 2024-01-26
 * @since : 0.1.0
 **/
@WebServlet(urlPatterns = "/my" ,loadOnStartup = 1)
public class MyConnectionPool extends HttpServlet {

    @Resource(name = "java:comp/env/jdbc/web_test")
    DataSource dataSource;

    @Override
    public void init() throws ServletException {
//        dataSource = (DataSource) getServletContext().getAttribute("pool");
        try {
            System.out.println("Init DataSource ::-> "+dataSource.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get !");
    }
}
