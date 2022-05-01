package Servlet.模板引擎;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet("/guess")
public class guessNum extends HttpServlet {
    //  表示要猜的数字
    private int result = 0;
    //  要是猜的次数
    private int count = 0;
    //  初始化模板引擎 定义一个全局变量 后续get post 方法中渲染都要用到~
    private TemplateEngine engine = new TemplateEngine();

    //    重写Servlet init()方法，Tomcat启动时自动加载，不需要Get Post中调用~
    @Override
    public void init() throws ServletException {
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(this.getServletContext());
        resolver.setPrefix("/WEB-INF/template/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("utf-8");
        engine.setTemplateResolver(resolver);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//  设置编码格式
        resp.setContentType("text/html;charset=utf-8");
//  初始化猜数字
        Random random = new Random();
        int result = random.nextInt();
        count = 0;
//  返回一个页面，开启一局新的游戏
//  将请求中的参数与模板中的变量建立联系
        WebContext webContext = new WebContext(req, resp, getServletContext());
        webContext.setVariable("newGame", true);
//  进行最终渲染(意思就是展示html内容至网页)
        engine.process("guessNum", webContext, resp.getWriter());
    }

    /**
     * 处理一次猜的过程
     * 每一次提交表单都会触发一次POST请求
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//  设置编码格式
        resp.setContentType("text/html;charset=utf-8");
//  从请求中获取数据
        String num = req.getParameter("num");
        int guess = Integer.parseInt(num);
        String res = "";
//  判断
        if (guess < result) {
            res = "猜小了";
        } else if (guess > result) {
            res = "猜大了";
        } else {
            res = "猜对了";
        }
        count++;
//  与模板中的参数建立连接
        WebContext webContext = new WebContext(req, resp, getServletContext());
        webContext.setVariable("ret", res);
        webContext.setVariable("count", count);
        webContext.setVariable("newGame", false);
//  渲染
        engine.process("guessNum", webContext, resp.getWriter());
    }
}
