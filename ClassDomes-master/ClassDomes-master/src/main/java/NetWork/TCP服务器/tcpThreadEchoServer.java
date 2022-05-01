package NetWork.TCP服务器;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 2022.04.10 周日
 */
public class tcpThreadEchoServer {
    //    1.绑定端口
    ServerSocket serverSocket = null;

    public tcpThreadEchoServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    //    2.启动服务器，并准备建立连接
    public void start() throws IOException {
        System.out.println("服务器已经启动！");
        while (true) {
            //   建立连接
            Socket socket = serverSocket.accept();
//            每次只要建立连接，就新创建一个线程去处理连接，避免因为processConnect中while循环不结束，而导致accept的阻塞
            Thread t1 = new Thread() {
                @Override
                public void run() {
//            处理连接
                    processConnect(socket);
                }
            };
            t1.start();
        }
    }

    private void processConnect(Socket socket) {
        System.out.printf("[%s,%d] 客户端已经连接\n", socket.getInetAddress().toString(), socket.getPort());

        try (InputStream inputStream = socket.getInputStream()) {
            try (OutputStream outputStream = socket.getOutputStream()) {
                while (true) {
//                   1.接收请求
                    Scanner scanner = new Scanner(inputStream);
//                    判断结束的条件
                    if (!scanner.hasNext()) {
                        System.out.printf("[%s,%d] 客户端已断开连接\n", socket.getInetAddress().toString(), socket.getPort());
                        break;
                    }
                    String request = scanner.next();
//                    2.根据请求处理响应
                    String response = process(request);
//                    3.将响应返回给客户端
//                    写到哪儿
                    PrintWriter printWriter = new PrintWriter(outputStream);
//                    写什么
                    printWriter.println(response);
//                    写入
                    printWriter.flush();
//                    4.控制台打印日志 便于分析
                    System.out.printf("req : %s , reps : %s\n",request,response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        tcpThreadEchoServer server = new tcpThreadEchoServer(9090);
        server.start();
    }
}
