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

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;

import java.io.IOException;

/**
 * @author : savindaJ
 * @date : 2024-01-19
 * @since : 0.1.0
 **/
//@WebFilter(urlPatterns = {"/item","/customer"})
public class FilterTwo extends HttpFilter {

    static {
        System.out.println("filter two static {}");
    }

    public FilterTwo() {
        System.out.println("filter two constructor !");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter Two !");
        chain.doFilter(req,res);
    }
}
