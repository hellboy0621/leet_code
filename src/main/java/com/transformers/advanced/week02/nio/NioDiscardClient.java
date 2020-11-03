package com.transformers.advanced.week02.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioDiscardClient {
    public static void startClient() throws IOException {
        InetSocketAddress address = new InetSocketAddress("localhost", 9999);
        // 1.获取通道
        SocketChannel socketChannel = SocketChannel.open(address);
        // 2.切换成⾮阻塞模式
        socketChannel.configureBlocking(false);
        //不断地⾃旋、等待连接完成，或者做⼀些其他的事情
        while (!socketChannel.finishConnect()) {

        }
        System.out.println("客户端连接成功");
        // 3.分配指定⼤⼩的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("hello nio discard world".getBytes());
        byteBuffer.flip();
        //发送到服务器
        socketChannel.write(byteBuffer);
        socketChannel.shutdownOutput();
        socketChannel.close();
    }

    public static void main(String[] args) throws IOException {
        startClient();
    }
}
