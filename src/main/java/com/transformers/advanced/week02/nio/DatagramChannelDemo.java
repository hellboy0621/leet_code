package com.transformers.advanced.week02.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelDemo {
    public static void main(String[] args) throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        datagramChannel.socket().bind(new InetSocketAddress(9999));

        //创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //从DatagramChannel读⼊，再写⼊到ByteBuffer缓冲区
        SocketAddress clientAddr = datagramChannel.receive(buffer);

        buffer.flip();
        datagramChannel.send(buffer, new InetSocketAddress("localhost", 9998));
        buffer.clear();

        datagramChannel.close();

    }
}
