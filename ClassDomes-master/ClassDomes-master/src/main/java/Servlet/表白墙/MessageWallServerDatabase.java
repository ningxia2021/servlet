package Servlet.表白墙;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * 使用数据库实现数据持久化存储
 * 1.首先要导入mysql-connector-java 包，记得在Project Structure->artifacts右侧的包中双击到lib里！
 * 2.建库建表
 *      create database messagewall;
 *      use messagewall;
 *      create table message(`from` varchar(1024),`to` varchar(1024),`message` varchar(1024));
 *
 * 3.实现jdbc工具类
 *
 */
@WebServlet("/data")
public class MessageWallServerDatabase extends HttpServlet {

}
