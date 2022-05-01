package NetWork.TCP服务器;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 2022.04.09 下午
 * TCP 回显服务器的实现
 * ServerSocket 专门给TCP服务器用的
 * Socket 既需要给服务器用 又需要给客户端用
 * 面向字节流，以字节为单位传输 不需要专门的类来表示”传输的包“
 */

public class tcpEchoServer {
    //    ServerSocket 是建立用来建立连接的
    ServerSocket serverSocket = null;

    //    构造方法来绑定端口
    public tcpEchoServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    //    启动服务
    public void start() throws IOException {
        System.out.println("服务器启动！");
        while (true) {
//            由于TCP是有链接的，不能像 UDP 一样 上来就读数据，而需要建立连接  （类比接电话）
//            accept() 是在创建连接。当没有客户端尝试建立连接时，此处的 accept() 就会阻塞。
//            accept() 一旦建立连接成功后会返回一个 Socket 对象， 后续和客户端之间的沟通，都是通过这个对象来完成的。
//            换句话讲，ServerSocket 的作用就是建立连接！
            Socket clientSocket = serverSocket.accept();
            processConnect(clientSocket);
        }
    }

    //    通讯的过程在这里实现
    private void processConnect(Socket clientSocket) {
//        打印一些日志信息 方便查看
        System.out.printf("[%s,%d] 客户端建立连接!\n", clientSocket.getInetAddress().toString(), clientSocket.getPort());
//        处理请求和响应
        //tcp是操作数据流来实现通信的 这里要读取 Socket 的流对象
        try (InputStream inputStream = clientSocket.getInputStream()) {
            try (OutputStream outputStream = clientSocket.getOutputStream()) {
//                循环的处理每个请求 分别返回响应
//                这里将 inputStream 放入scanner缓冲区内 然后循环读取
                Scanner scanner = new Scanner(inputStream);
                while (true) {
//                    scanner.hasNext()作用就是"当我们想退出用hasNext作条件的while()循环时，那么要么控制台手工输入ctrl+z，要么 while(!sc.hasNext("#"))约定当一行输入#时退出"
//                    1.读取请求
                    if (!scanner.hasNext()) {
                        System.out.printf("[%s,%d] 客户端断开连接!\n", clientSocket.getInetAddress().toString(), clientSocket.getPort());
                        break;
                    }
//                   读取scanner中内容
                    String request = scanner.next();
                    System.out.println(request);
//                    2.根据请求计算响应
                    String response = process(request);
                    System.out.println(response);
//                    3.把响应返回给客户端
//                    为了方便，可以使用 PrintWriter 把 OutputStream 包裹一下
//                    PrintWriter(OutputStream out) 根据现有的 OutputStream 创建不带自动行刷新的新 PrintWriter。
                    PrintWriter printWriter = new PrintWriter(outputStream);
                    printWriter.println(response);
//                    刷新缓冲区
                    printWriter.flush();
//                    打印日志
                    System.out.printf("[%s,%d] req : %s , reps : %s\n", clientSocket.getInetAddress().toString(), clientSocket.getPort(), request, response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
//                此处要记得关闭
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        tcpEchoServer tcpServer = new tcpEchoServer(9090);
        tcpServer.start();
    }

}
