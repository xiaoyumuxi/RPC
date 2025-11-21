import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyServer {
    public static void main(String[] args) throws Exception {
        // 1. 准备两个线程组
        // bossGroup: 只负责连接请求 (1个线程够了)
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // workerGroup: 负责真正的读写业务 (默认线程数是 CPU核数 * 2)
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 2. 创建服务器启动助手
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup) // 设置两个线程组
                    .channel(NioServerSocketChannel.class) // 指定使用 NIO 的服务端通道
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 定义每个连接来了之后怎么处理
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            // 这里是核心！我们把 Handler 一个个加到 Pipeline 流水线上
                            ChannelPipeline pipeline =  ch.pipeline();

                            // 添加内置的 String 解码器和编码器 (省去了处理 ByteBuf 的麻烦)
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());

                            // 添加我们自己的业务处理逻辑
                            pipeline.addLast(new SimpleChannelInboundHandler<String>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                    // 打印接收到的消息
                                    System.out.println("收到客户端消息: " + msg);
                                    // 回复消息
                                    ctx.writeAndFlush("Netty收到了你的消息: " + msg);
                                }
                            });
                        }
                    });

            System.out.println("服务端启动中...");

            // 3. 绑定端口，同步等待成功
            ChannelFuture f = b.bind(8080).sync();

            // 4. 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } finally {
            // 优雅退出
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}