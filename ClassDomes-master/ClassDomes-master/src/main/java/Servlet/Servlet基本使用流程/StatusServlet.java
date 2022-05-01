package Servlet.Servlet基本使用流程;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/status")
public class StatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
//        1.让用户传入一个请求
//        2.请求在query string 中带一个参数，就表示响应的状态码
//        3.然后根据用户的输入，返回不同的状态码响应
        String statusString = req.getParameter("status");
        if (statusString == null||statusString.equals("")) {
            resp.getWriter().write("当前请求的参数 status 缺失");
            return;
        }
        resp.setStatus(Integer.parseInt(statusString));
        resp.getWriter().write("status : " + statusString);
    }
}
