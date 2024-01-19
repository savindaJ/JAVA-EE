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
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

/**
 * @author : savindaJ
 * @date : 2024-01-19
 * @since : 0.1.0
 **/
@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*<Resource name="jdbc/mydb" auth="Container" type="javax.sql.DataSource"
          username="root" password="root" driverClassName="com.mysql.jdbc.Driver"
          maxActive="1000" maxIdle="100" maxWait="10000"
          url="jdbc:mysql://localhost:3306/mydatabase"
          factory="org.apache.tomcat.jdbc.pool.DataSourceFactory" />*/

        /*<resource-ref>
        <description>DB Connection</description>
        <res-ref-name>jdbc/mydb</res-ref-name>
        <res-type>javax.sql.DataSource</res-type>
        <res-auth>Container</res-auth>
        </resource-ref>*/

        /*Context initContext = new InitialContext();
        DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/mydb");*/

        ServletContext context = getServletContext();
        Connection connection = (Connection) context.getAttribute("connection");
        System.out.println(connection);
    }
}
