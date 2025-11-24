package RPC_entity;

public class RpcRequest {
    private String interfaceName;
    private String methodName;
    private Object[] args;
    private Class<?>[] parameterTypes;
    private int requestId;
}
