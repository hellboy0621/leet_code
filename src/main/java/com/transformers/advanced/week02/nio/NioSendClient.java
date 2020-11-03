package com.transformers.advanced.week02.nio;

import java.io.File;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class NioSendClient {
    private Charset charset = StandardCharsets.UTF_8;

    public void sendFile() {
        try {
            String srcPath = "D:/00_Java_advanced/sandbox/TestDemo.txt";
            String destPath = "channel.txt";
            File file = new File(srcPath);
            if (!file.exists()) {
                System.out.println("文件不存在");
                return;
            }
            FileChannel fileChannel = new FileInputStream(file).getChannel();
            SocketChannel socketChannel = SocketChannel.open();
            InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 9999);
            socketChannel.socket().connect(inetSocketAddress);
            socketChannel.configureBlocking(false);
            while (!socketChannel.finishConnect()) {

            }
            System.out.println("client成功连接服务器");
            // 发送文件名称
            ByteBuffer fileNameByteBuffer = StandardCharsets.UTF_8.encode(destPath);
            socketChannel.write(fileNameByteBuffer);
            // 发送文件长度
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.putLong(file.length());
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
            // 发送文件内容
            System.out.println("开始传输文件");
            int length = 0;
            long progress = 0;
            while ((length = fileChannel.read(buffer)) > 0) {
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();
                progress += length;
                System.out.println("| " + (100 * progress / file.length()) + "%");
            }
            if (length == -1) {
                fileChannel.close();
                // 在SocketChannel传输通道关闭前，尽量发送一个输出结束标志
                socketChannel.shutdownOutput();
                socketChannel.close();
            }
            System.out.println("=========文件传输成功=========");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendFile1() {
        try {
            String srcPath = "D:/00_Java_advanced/sandbox/TestDemo.txt";
            System.out.println("srcPath=" + srcPath);

            String destFile = "channel.txt";
            System.out.println("destFile=" + destFile);

            File file = new File(srcPath);
            if (!file.exists()) {
                System.out.println("文件不存在");
                return;
            }
            FileChannel fileChannel = new FileInputStream(file).getChannel();

            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.socket().connect(new InetSocketAddress("localhost", 9999));
            socketChannel.configureBlocking(false);
            System.out.println("Cliect 成功连接服务端");

            while (!socketChannel.finishConnect()) {
                //不断的自旋、等待，或者做一些其他的事情
            }

            //发送文件名称
            ByteBuffer fileNameByteBuffer = charset.encode(destFile);
            socketChannel.write(fileNameByteBuffer);

            //发送文件长度
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.putLong(file.length());

            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();

            //发送文件内容
            System.out.println("开始传输文件");
            int length = 0;
            long progress = 0;
            while ((length = fileChannel.read(buffer)) > 0) {
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();
                progress += length;
                System.out.println("| " + (100 * progress / file.length()) + "% |");
            }

            if (length == -1) {
                fileChannel.close();
                socketChannel.shutdownOutput();
                socketChannel.close();
            }
            System.out.println("======== 文件传输成功 ========");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NioSendClient client = new NioSendClient();
        client.sendFile1();
    }
}
