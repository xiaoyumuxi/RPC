package RPC_entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class JsonSerializer implements Serializer {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(Object obj) {
        try {
            return objectMapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            log.error("序列化时发生错误: {}", e.getMessage());
            throw new RuntimeException("Serializer error", e);
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try {
            return objectMapper.readValue(bytes, clazz);
        } catch (IOException e) {
            log.error("反序列化时发生错误: {}", e.getMessage());
            throw new RuntimeException("Deserializer error", e);
        }
    }

    @Override
    public int getCode() {
        return SerializeType.JSON.getCode();
    }
}
