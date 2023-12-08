package lk.ijse.lifeCycle;

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
public class MyServletTwo extends HttpServlet {
    {
        System.out.println("instance myServlet 2 ! -------------------------------------");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init myServlet 2 invoke !");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get method myServlet 2 !");
    }

    @Override
    public void destroy() {
        System.out.println("destroy myServlet 2 !");
    }
}
