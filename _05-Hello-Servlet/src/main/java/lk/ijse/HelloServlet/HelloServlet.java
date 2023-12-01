package lk.ijse.HelloServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>get method invoke</h1>");
        System.out.println("get() invoke !");
//        get servlet config !
        ServletConfig config = getServletConfig();
//        and get servlet config name parameter value
//        this value stored in web.xml file !
        String city = config.getInitParameter("city");
        System.out.println(city);
        System.out.println(config.getInitParameter("password"));
        System.out.println(config.getInitParameter("user"));
        System.out.println(config.getInitParameter("URL"));


//        get context parameter value !

        ServletContext servletContext = getServletContext();
        String password = servletContext.getInitParameter("password");
        System.out.println(password);
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init created !");
    }
}
