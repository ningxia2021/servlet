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
import java.util.ArrayList;
import java.util.List;

class Person{
    private String name;
    private String phone;

    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}

@WebServlet("/each")
public class thymeleafEachServlet extends HttpServlet {
//    初始化模板引擎
    private TemplateEngine engine = new TemplateEngine();

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
        resp.setContentType("text/html;charset=utf-8");
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("干","13119542131"));
        persons.add(new Person("哈哈","15252456932"));
        persons.add(new Person("精密","18895323698"));
        persons.add(new Person("努力","123456789"));
        persons.add(new Person("学习","13134542131"));
        persons.add(new Person("高博","15285325652"));
        persons.add(new Person("外星人","18895323698"));
        persons.add(new Person("孙悟空","123456789"));
        WebContext webContext = new WebContext(req, resp, getServletContext());
        webContext.setVariable("persons",persons);
        engine.process("thymeleafEach", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
