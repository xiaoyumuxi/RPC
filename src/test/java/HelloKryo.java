import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import lombok.extern.slf4j.Slf4j;

import java.io.*;


@Slf4j
public class HelloKryo {
    static public void main (String[] args) throws Exception {
        Kryo kryo = new Kryo();
        kryo.register(SomeClass.class);//用SomeClass的模版进行序列化

        SomeClass object = new SomeClass();
        object.value = "Hello Kryo!";//构建信息

        Output output = new Output(new FileOutputStream("file.bin"));
        kryo.writeObject(output, object);//向output中写入obj
        output.close();

        Input input = new Input(new FileInputStream("file.bin"));
        SomeClass object2 = kryo.readObject(input, SomeClass.class);//读入流按照对应模板进行解析
        log.info(object2.toString());
        log.info(object2.value);
        input.close();
    }
    static public class SomeClass {
        String value;
    }
}