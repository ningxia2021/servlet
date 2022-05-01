package NetWork.TCP服务器;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * 2022.04.10 凌晨
 * tcp 回显服务器客户端
 */
public class tcpEchoClient {
    //   此处不用手动给定端口号，让操作系统自己分配
    private Socket socket = null;

    public tcpEchoClient(String ServerIP, int ServerPort) throws IOException {
        /**
         * 这里传入的 IP 和 port 的含义不是绑定，而是表示和这个IP 和 port 建立连接！
         * 对于 UDP 的 Socket 来说，构造方法指定端口表示自己绑定的哪个端口
         * 对于 TCP 的 ServerSocket 来说，构造方法指定的端口也是表示自己绑定的哪个端口
         * 对于 TCP 的 Socket 来说，构造方法表示要连接的服务器的端口
         * 调用这个构造方法就会和服务器建立连接！
         */
        socket = new Socket(ServerIP, ServerPort);
    }

    public void start() {
        System.out.printf("[%s,%d] 客户端启动！\n", socket.getInetAddress().toString(), socket.getPort());
        Scanner scanner = new Scanner(System.in);
        try (InputStream inputStream = socket.getInputStream()) {
            try (OutputStream outputStream = socket.getOutputStream()) {
                while (true) {
//            1.从控制台读取字符串
                    System.out.print("->");
                    String request = scanner.nextLine();
//            2.根据读取的字符串，构造请求，把请求发送给服务器
                    PrintWriter printWriter = new PrintWriter(outputStream);
//                    将 request 写进 outputStream
//                    printWriter.write(request);
                    printWriter.println(request);
//                    刷新缓冲区
                    printWriter.flush();
//            3.从服务器读取响应，并解析
                    Scanner res = new Scanner(inputStream);
                    String response = res.next();
//            3.把结果显示到服务台上
                    System.out.printf("rep : %s , reps : %s\n", request, response);
                }
            }
        } catch (IOException E) {
            E.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        tcpEchoClient client = new tcpEchoClient("127.0.0.1",9090);
        client.start();
    }
}
