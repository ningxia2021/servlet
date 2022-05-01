package Servlet.登录案例;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/index")
public class indexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
//        1. 判断当前用户是否已经登录了 也就是 请求中有没有sessionId 以及sessionId是否合法
//        根据当前用户请求中的sessionId获取到用户信息 并显示到页面上
//        这里只是获取sessionId，没有就说明没有登录成功。不需要重新创建，因此参数是false
        HttpSession httpSession = req.getSession(false);
        if (httpSession==null){
            resp.sendRedirect("login.html");
            return;
        }
//        2. 如果用户已经登录，就可以从httpsession 中拿到用户信息了
        String username = (String)httpSession.getAttribute("username");
        int loginCount = (Integer)httpSession.getAttribute("loginCount");
        httpSession.setAttribute("loginCount",loginCount+1);
//        3. 返回一个 html 页面，让这个页面中带有用户信息
        resp.getWriter().write("用户名 : "+username+" 登录次数为 : "+loginCount);
    }
}
