package Servlet.Servlet基本使用流程;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取url中的query String
 */
@WebServlet("/getParameters")
public class GetParameter extends HelloServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        定义响应返回的类型
        resp.setContentType("text/html;charset=utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username==null || username.equals("")){
//            有建没有引用值的情况 或者 没有 Parameters 的情况 加一些处理参数不存在的逻辑
            System.out.println("参数不存在");
        }
        resp.getWriter().write(String.format("username:%s password:%s",username,password));
        resp.getWriter().write("</br>");
    }
}

