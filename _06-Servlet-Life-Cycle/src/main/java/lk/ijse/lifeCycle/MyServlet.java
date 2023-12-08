package lk.ijse.lifeCycle;

import javax.servlet.ServletConfig;
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

    {
        System.out.println("invoke instance block !");
    }

    static {
        System.out.println("invoke static block !");
    }

    public MyServlet() {
        System.out.println("invoke constructor !");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init ServletConfig method invoke !");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init method invoke !");
        System.out.println("Ready to Serve !");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get method invoke !");
    }

    @Override
    public void destroy() {
        System.out.println("destroy method invoke !");
    }
}
