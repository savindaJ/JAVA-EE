package lk.ijse.annotation;

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
public class MyServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("init !");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do get () !");
    }
}
