package file;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 字符写
 */
public class demo8 {
    public static void main(String[] args) {
        try (Writer writer = new FileWriter("./test.txt")){
            writer.write("hahahahhahha");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
