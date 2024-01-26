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

package lk.ijse.conPool.api;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author : savindaJ
 * @date : 2024-01-19
 * @since : 0.1.0
 **/
@WebListener(value = "myListener") // identify listener
public class MyListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        BasicDataSource dbcp = (BasicDataSource) context.getAttribute("dbcp");
        try {
            /**when app destroyed
             * or run deployed
             * close a connection pool*/

            dbcp.close();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        System.out.println("when app closed !");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        System.out.println("when app deployed !");
        BasicDataSource dbpc = new BasicDataSource(); // create pool
        dbpc.setUsername("root");
        dbpc.setUrl("jdbc:mysql://localhost:3306/web_test");
        dbpc.setPassword("80221474");
        dbpc.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dbpc.setInitialSize(2);
        dbpc.setMaxTotal(5);
        try {
            Connection connection = dbpc.getConnection();
            System.out.println("my pool"+connection);
            context.setAttribute("connection",connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// https://www.youtube.com/watch?v=Ujei6LjYDKs