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
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author : savindaJ
 * @date : 2024-01-26
 * @since : 0.1.0
 **/
@WebListener
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("when app deployed !");
        //        ServletContext context = sce.getServletContext();
        //
        //        try {
        //            Context initCtx = new InitialContext();
        //            DataSource envCtx = (DataSource) initCtx.lookup("java:comp/env/jdbc/web_test");
        //            System.out.println("Created context Connection ::-> "+envCtx.getConnection());
        //            context.setAttribute("pool",envCtx);
        //        } catch (Exception e) {
        //            System.out.println(e.getMessage());
        //        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("when app destroyed !");
    }
}
