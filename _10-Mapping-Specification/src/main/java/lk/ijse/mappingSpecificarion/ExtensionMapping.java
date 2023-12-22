/*
 * Copyright (c) 2023.
 * All Rights Reserved
 *  * You may use, distribute and modify this code under the
 *  * terms of the XYZ license, which unfortunately won't be
 *  * written for another century.
 *  *
 *  * You should have received a copy of the XYZ license with
 *  * this file. If not, please write to: , or visit :
 */

package lk.ijse.mappingSpecificarion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author : savindaJ
 * @date : 2023-12-22
 * @since : 0.1.0
 **/

@WebServlet(urlPatterns = "*.php")
//@WebServlet(urlPatterns = "/*.php")
//@WebServlet(urlPatterns = "abc*.php")
public class ExtensionMapping extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>Extension Mapping ! (*.php)</h1>");
        resp.getWriter().println("<h1>http://localhost:8080/map/abc.php/.php</h1>");
        resp.getWriter().println("<h1>/*.php @ abc*.php (Invalid URL Pattern !)</h1>");
    }
}
