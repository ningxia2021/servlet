package Servlet.Servlet基本使用流程;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 2022.04.14
 * 通过 HttpServletRequest 的方法将 HTTP 请求以 HTML 的请示返回至浏览器
 * 展示 HttpRequestServlet 的功能
 */
@WebServlet("/showRequest")
public class ShowRequestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
//        把生成的响应拼接起来 按照HTML格式
        StringBuilder responseBody = new StringBuilder();
        responseBody.append(req.getProtocol());
        responseBody.append("</br>");
        responseBody.append(req.getMethod());
        responseBody.append("</br>");
        responseBody.append(req.getRequestURI());
        responseBody.append("</br>");
        responseBody.append(req.getContextPath());
        responseBody.append("</br>");
        responseBody.append(req.getQueryString());
        responseBody.append("</br>");
        responseBody.append("<h3>headers:</h3>");
        Enumeration<String> headerNames = req.getHeaderNames();
//        循环获取所有的header
        while (headerNames.hasMoreElements()) {
            responseBody.append(headerNames.nextElement() + ": " + req.getHeader(headerNames.nextElement()));
            responseBody.append("</br>");
        }
        resp.getWriter().write(responseBody.toString());
    }
    /**
     * 解析完成后，就可以通过  http://localhost:8080/java_Web_exploded/showRequest 来获取响应
     */
}
