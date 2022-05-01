package Servlet.Servlet基本使用流程;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 2022.04.14
 * 构造可以调用不同方法的请求
 */
@WebServlet("/method")
public class MethodServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        统一编码格式
//        这里格式编码的要求一定要在前面，不然后面的响应内容就不会按照编码要求来
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("GET 响应");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("POST 响应");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("PUT 响应");
    }
}
