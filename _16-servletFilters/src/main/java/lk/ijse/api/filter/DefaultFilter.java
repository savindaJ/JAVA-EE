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

package lk.ijse.api.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author : savindaJ
 * @date : 2024-01-19
 * @since : 0.1.0
 **/
//@WebFilter(urlPatterns = "/*")
public class DefaultFilter extends HttpFilter {

    static {
        System.out.println("default filter static {}");
    }

    public DefaultFilter() {
        System.out.println("default filter constructor !");
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        System.out.println("incoming req !");

        System.out.println("Default Filter !");

        // send req to servlet , this method without use a app , req break in this filter !
        chain.doFilter(req,resp);

        resp.setHeader("Access-Control-Allow-Origin","*");
        resp.setHeader("Access-Control-Allow-Headers","Content-Type");
        resp.setHeader("Access-Control-Allow-Methods","DELETE,PUT");
        System.out.println("request out going response !");
    }
}
