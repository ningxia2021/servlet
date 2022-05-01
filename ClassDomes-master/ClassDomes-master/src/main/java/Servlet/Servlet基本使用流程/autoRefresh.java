package Servlet.Servlet基本使用流程;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/autoRefresh")
public class autoRefresh extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
//        这是我没有想到的  刷新页面是在 setHeader 中实现
        resp.setHeader("Refresh","1");
        resp.getWriter().write("timeStamp :" + System.currentTimeMillis());

    }
}
