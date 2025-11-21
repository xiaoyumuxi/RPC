import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class POJO {
    // 请求对象
    @Data // Lombok, 如果没有就手写 getter/setter
    @AllArgsConstructor
    @NoArgsConstructor
    static public class RpcRequest {
        private String requestId;
        private String methodName;
    }

    // 响应对象
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static public class RpcResponse {
        private String requestId;
        private String result;
    }
}
