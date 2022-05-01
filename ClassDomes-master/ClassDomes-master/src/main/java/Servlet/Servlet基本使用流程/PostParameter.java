package Servlet.Servlet基本使用流程;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 使用 HttpServletRequest 这个类最常用的，就是获取请求中的参数
 * 1. 参数是query string 形式，通过req.getParameter() 获取
 * 2. 参数是body (application/x-www-form-urlencoded) 通过req.getParameter() 获取
 * 3. 参数是body (application/json) 先把整个body读出来，然后再使用JSON库 jackson来解析，转化成string 最后读出来
 */
@WebServlet("/postParameter")
public class PostParameter extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        格式
        resp.setContentType("text/html;charset=utf-8");
//        post请求的参数在body中 query string格式
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        返回响应
        resp.getWriter().write(String.format("username : %s , password : %s <br>",username,password));
//        接下来去网页body中构造post请求
    }
}
