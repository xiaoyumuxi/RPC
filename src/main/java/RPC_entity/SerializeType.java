package RPC_entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SerializeType{
    JSON(1),
    Kryo(2);

    private final int code;
}
