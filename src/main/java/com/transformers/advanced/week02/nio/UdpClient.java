package com.transformers.advanced.week02.nio;

import org.apache.http.client.utils.DateUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UdpClient {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public void send() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        System.out.println("UDP客户端启动成功！");
        System.out.println("请输入发送内容：");
        while (scanner.hasNext()) {
            String next = scanner.next();

            buffer.put((SDF.format(new Date()) + ">>" + next).getBytes());
            buffer.flip();
            // 通过DatagramChannel数据报通道发送数据
            datagramChannel.send(buffer, new InetSocketAddress("localhost", 8888));
            buffer.clear();
        }
        datagramChannel.close();
    }

    public static void main(String[] args) throws IOException {
        new UdpClient().send();
    }
}
