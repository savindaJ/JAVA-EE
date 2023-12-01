package lk.ijse.webap2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : savindaJ
 * @date : 12/1/2023
 * @since : 0.1.0
 **/
public class ServletTow extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>Servlet 1 !</h1>");
        resp.getWriter().println("<h1>HTTP GET !</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>Servlet 1 !</h1>");
        resp.getWriter().println("<h1>HTTP POST !</h1>");
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>Servlet 1 !</h1>");
        resp.getWriter().println("<h1>HTTP OPTION !</h1>");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>Servlet 1 !</h1>");
        resp.getWriter().println("<h1>HTTP DELETE !</h1>");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>Servlet 1 !</h1>");
        resp.getWriter().println("<h1>HTTP PUT !</h1>");
    }
}
