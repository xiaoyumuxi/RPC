package Test;

import RPC_entity.serialize.JsonSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class Test1 {


    public static void main(String[] args) {
        JsonSerializer jsonSerializer = new JsonSerializer();
        User user = new User("yaojingxi", 22);
        byte[] msg = jsonSerializer.serialize(user);
        log.info("User: {}", new String(msg));
        User target = jsonSerializer.deserialize(msg, User.class);
        log.info("Raw Bytes: {}", Arrays.toString(msg));
        log.info("反序列化结果: name={}, age={}", target.getName(), target.getAge());
    }
}


//Jackson 反序列化的原理是这样的：
//买房（实例化）：先调用类的无参构造函数 new User()，创建一个空的 User 对象。
//装修（赋值）：通过反射调用 setName() 和 setAge()，把 JSON 里的数据填进去。
@AllArgsConstructor
@NoArgsConstructor//需要注意必须要一个无餐构造器
@Getter
@Setter
class User {
    String name;
    int age;
}