package lk.ijse.webapp03;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : savindaJ
 * @date : 12/1/2023
 * @since : 0.1.0
 **/
@WebServlet(name = "Test",urlPatterns = "/test" , loadOnStartup = 1 , initParams = {
        @WebInitParam(name = "user",value = "root"),
        @WebInitParam(name = "password",value = "80221474"),
        @WebInitParam(name = "URL",value = "jdbc:mysql://localhost:3306/web_test")
})
public class Test extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
