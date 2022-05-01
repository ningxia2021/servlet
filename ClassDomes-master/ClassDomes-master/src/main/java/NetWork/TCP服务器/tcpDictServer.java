package NetWork.TCP服务器;

import java.io.IOException;
import java.util.HashMap;

public class tcpDictServer extends tcpEchoServer{

    HashMap<String,String> hashMap = new HashMap<>();
    public tcpDictServer(int port) throws IOException {
        super(port);
        hashMap.put("cat","小猫");
        hashMap.put("dog","小狗");
        hashMap.put("fuck","卧槽");
    }

    @Override
    public String process(String request) {
        return hashMap.getOrDefault(request,"没有查到这个词");
    }

    public static void main(String[] args) throws IOException {
        tcpDictServer dict = new tcpDictServer(9090);
        dict.start();
    }
}
