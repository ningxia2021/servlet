package NetWork.UDP服务器;

import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;

/**
 * 2022.04.09 周六
 * 实现udp 网络套接字编程
 */
//基本的接收发送过程继承udpServer中
public class udpDictionaryServer extends udpEchoServer {
    HashMap<String,String> hashMap = new HashMap<>();

//    这是字典udp的构造方法，需要添加一些词组进去
    public udpDictionaryServer(int port) throws SocketException {
        super(port);
        hashMap.put("cat","小猫");
        hashMap.put("dog","小狗");
        hashMap.put("fuck","卧槽");
    }

//    重写根据请求计算响应的过程
    @Override
    public String process(String req) {
       return hashMap.getOrDefault(req,"没有这个词");
    }

    public static void main(String[] args) throws IOException {
        udpDictionaryServer dict = new udpDictionaryServer(9090);
        dict.start();
    }
}
