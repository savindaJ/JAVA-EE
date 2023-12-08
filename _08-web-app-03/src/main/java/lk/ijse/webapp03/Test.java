package lk.ijse.webapp03;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    private Connection connection;

    String URL;
    String user;
    String password;

    @Override
    public void init() throws ServletException {
        ServletConfig servletConfig = getServletConfig();
        user = servletConfig.getInitParameter("user");
        password = servletConfig.getInitParameter("password");
        URL = servletConfig.getInitParameter("URL");
        System.out.println(URL);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get !");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL,user,password);

            String cusId = req.getParameter("cusId");
            String name = req.getParameter("name");
            String address = req.getParameter("address");
            String salary = req.getParameter("salary");

            System.out.println(cusId+name+address+salary);

            PreparedStatement pstm = connection.prepareStatement("insert into customer values(?,?,?,?)");
            pstm.setString(1,cusId);
            pstm.setString(2,name);
            pstm.setString(3,address);
            pstm.setDouble(4, Double.parseDouble(salary));

            int execute = pstm.executeUpdate();

            if (execute>0){
                System.out.println("saved()!");
                resp.getWriter().println("<h1>saved !</h1>");
                connection.close();
            }else {
                System.out.println("not saved()!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("post !");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String cusId = req.getParameter("cusId");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String salary = req.getParameter("salary");

        PreparedStatement pstm = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL,user,password);
            pstm = connection.prepareStatement("update customer set name=?,address=?,salary=? where cus_id=?");
            pstm.setString(1,name);
            pstm.setString(2,address);
            pstm.setDouble(3, Double.parseDouble(salary));
            pstm.setString(4, cusId);

            int execute = pstm.executeUpdate();

            if (execute>0){
                System.out.println("updated !");
                resp.getWriter().println("<h1>updated !</h1>");
                connection.close();
            }else {
                System.out.println("not update !");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cusId = req.getParameter("cusId");

        PreparedStatement pstm = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL,user,password);
            pstm = connection.prepareStatement("delete from customer where cus_id=?");
            pstm.setString(1,cusId);

            int execute = pstm.executeUpdate();

            if (execute>0){
                System.out.println("deleted !");
                connection.close();
                resp.getWriter().println("<h1>deleted !</h1>");
            }else {
                System.out.println("not deleted !");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
