package com.transformers.advanced.week02.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelDemo {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
        while (!socketChannel.finishConnect()) {
            //不断地⾃旋、等待，或者做⼀些其他的事情……
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read = socketChannel.read(byteBuffer);
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        //终⽌输出⽅法，向对⽅发送⼀个输出的结束标志
        socketChannel.shutdownOutput();
        //关闭套接字连接
        socketChannel.close();
    }
}
