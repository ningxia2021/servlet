package Servlet.Servlet基本使用流程;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/redirect")
public class Redirect extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        状态码302
        resp.setStatus(302);
        resp.setHeader("Location","https://www.baidu.com");
//        resp.sendRedirect("https://www.baidu.com"); 也可以实现

    }
}
