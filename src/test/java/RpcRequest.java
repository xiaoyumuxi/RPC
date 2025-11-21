import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString//重写类String方法——方便debug
public class RpcRequest implements Serializable {
    //序列化ID，用来标识这个类
    private static final long serialVersionUID = 666L;
    //请求id，用来区分连接
    private String requestId;
    //定位方法
    private String interfaceName;
    private String methodName;
    //两者配合类获取到参数内容
    private Object[] parameters;
    private Class<?>[] paramTypes;
    //协议控制——流类型说明
    private RpcMessageTypeEnum rpcMessageTypeEnum;
}
