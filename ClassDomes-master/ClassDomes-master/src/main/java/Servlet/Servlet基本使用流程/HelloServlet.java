package Servlet.Servlet基本使用流程;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @WebServlet("/hello")
 * 建立 类和一个 HTTP 特定请求进行关联 （根据 HTTP 请求 URL 的路径来进行关联）
 * 如果 Tomcat 收到一个路径为/hello 的请求，就会调用 HelloServlet 这个类的代码
 *  如果这个请求是 GET 请求，就会调用 HelloServlet.doGet 方法
 *  如果这个请求是 POST 请求，就会调用 HelloServlet.doPost 方法
 */
@WebServlet("/hello")
// 创建的代码需要继承HttpServlet才能被tomcat给调用到
public class HelloServlet extends HttpServlet {
    /**
     * doGet() 是HttpServlet 提供的方法
     * 参数 HttpServletRequest HttpServletResponse
     * doGet() 就是去重写方法实现 根据请求生成响应
     * 此处重写doGet之后，并不需要手动调用doGet，而是由Tomcat自动调用，也不需要手动创建HelloServlet实例，也是由Tomcat自动创建实例
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 这里调用父类代码的操作，要注释掉。
         */
//        super.doGet(req, resp);
        /**
         * 这个操作就是往 http 响应body 中写入 "hello servlet" 字符串
         */
        resp.getWriter().write("Hello Servlet");
    }

    /**
     * 这个代码和以往的代码有很大的区别
     * 以往都是要从main方法运行程序，Servlet中 main方法在 Tomcat中，由Tomcat在合适的时机自动调用
     * 像这样的情况，我们就可以把Servlet理解成一个框架。
     * 这个代码的核心流程已经有了，只不过就是在核心流程中，其中的某些环节留出来由程序员结合实际开发情况自己定义，在程序运行时自动调用
     */
}
