package Servlet.登录案例;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 实现用户登录的案例
 * 1.首先要有一个html页面，包含用户名密码的输入框，以及登录按钮
 * 2.然后要有loginServlet，处理登录请求
 * 3.最后再搞一个indexServlet，模拟登录完成后，跳转到主页，在这个主页获取一些用户的身份信息
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        0. 设置响应返回的格式
        resp.setContentType("text/html;charset=utf-8");
//        1. 从请求的body中读取用户名密码
//           post的请求 参数在body中
//           表单提交的参数，是形如 query string 的样式
//           所以 通过req.getParameter()获取参数的值
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        2. 判定用户名密码是否正确
//           此处写死，就暂且不读数据库了
        if (!username.equals("zhangsan") || !password.equals("123")){
            resp.getWriter().write("登录失败，请重新登录");
            return;
        }
//        3. 登录成功则创建出一个会话来
//            参数 true 的作用是，会话不存在就会创建！
//            会话是根据请求的sessionId来查的，sessionId是在Cookie中的
//            此时是首次登录，请求中是没有Cookie的，则需要创建一个新的会话！此处就会触发找不到就创建的这样的流程！
//            同时，req.getSession(true); 这里进行的操作是：
//            先创建一个httpSession对象作为(value)  (这里应该是把用户状态信息存在这个对象中了)
//            再创建一个随机的字符串，作为sessionId (key)
//            把这个key 和 value 插入到hash表中
//            同时，把这个生成的sessionId 通过 Set-Cookie字段返回给浏览器
        HttpSession httpSession = req.getSession(true);
//        还可以存入程序员自定义的数据 可以存入用户名 登录次数等
        httpSession.setAttribute("username","zhangsan");
        httpSession.setAttribute("loginCount",0);
//        4. 登录成功后重定向到主页
        resp.sendRedirect("index");
    }
}
