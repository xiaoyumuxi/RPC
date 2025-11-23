package RPC_entity;

import java.io.Serializable;

public interface Serializer {
    /**
     * 把对象序列化成字节数组
     */
    byte[] serialize(Object obj);

    /**
     * 把字节数组反序列化成对象
     */
    <T> T deserialize(byte[] bytes, Class<T> clazz);

    /**
     * 获取序列化器的标识码（用于协议头中的 Serializer Type 字段）
     * 1代表JSON, 2代表Kryo...
     */
    int getCode();
}
