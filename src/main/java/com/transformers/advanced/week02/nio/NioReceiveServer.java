package com.transformers.advanced.week02.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NioReceiveServer {

    /**
     * 内部类，服务器端保存的客户端对象，对应一个客户端文件
     */
    static class Client {
        // 文件名称
        String fileName;
        // 长度
        long fileLength;
        // 开始传输时间
        long startTime;
        // 客户端地址
        InetSocketAddress remoteAddress;
        // 输出的文件通道
        FileChannel outChannel;
    }

    ByteBuffer buffer = ByteBuffer.allocate(1024);
    // 使⽤Map保存每个⽂件传输，当OP_READ可读时，根据通道找到对应的对象
    Map<SelectableChannel, Client> clientMap = new HashMap<>();

    public void startServer() throws IOException {
        // 1.获取选择器
        Selector selector = Selector.open();
        // 2.获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        // 3.设置为⾮阻塞
        serverSocketChannel.configureBlocking(false);
        // 4.绑定连接
        InetSocketAddress address = new InetSocketAddress(9999);
        serverSocket.bind(address);
        // 5.将通道注册到选择器上,并注册的IO事件为：“接收新连接”
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("server channel is listening...");
        // 6.选择感兴趣的IO就绪事件（选择键集合）
        while (selector.select() > 0) {
            // 7.获取选择键集合
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while (iter.hasNext()) {
                // 8.获取单个的选择键，并处理
                SelectionKey key = iter.next();
                // 9.判断key是具体的什么事件，是否为新连接事件
                if (key.isAcceptable()) {
                    // 10.若接受的事件是“新连接”事件,就获取客户端新连接
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = server.accept();
                    if (socketChannel == null) {
                        continue;
                    }
                    // 11.客户端新连接，切换为⾮阻塞模式
                    socketChannel.configureBlocking(false);
                    // 12.将客户端新连接通道注册到选择器上
                    SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
                    // 为每⼀条传输通道，建⽴⼀个Client客户端对象，放⼊map，供后⾯使⽤
                    Client client = new Client();
                    client.remoteAddress = (InetSocketAddress) socketChannel.getRemoteAddress();
                    clientMap.put(socketChannel, client);
                    System.out.println(socketChannel.getRemoteAddress() + "连接成功...");
                } else if (key.isReadable()) {
                    // 13.若接收的事件是“数据可读”事件,就读取客户端新连接
                    processData(key);
                }
                // NIO的特点只会累加，已选择的键的集合不会删除
                // 如果不删除，下⼀次⼜会被select函数选中
                iter.remove();
            }
        }
    }

    /**
     * 处理客户端传输过来的数据
     */
    private void processData(SelectionKey key) throws IOException {
        Client client = clientMap.get(key.channel());
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int num = 0;
        try {
            buffer.clear();
            while ((num = socketChannel.read(buffer)) > 0) {
                buffer.flip();
                if (client.fileName == null) {
                    // 客户端发送过来的，⾸先是⽂件名
                    // 根据⽂件名，创建服务器端的⽂件，将⽂件通道保存到客户端
                    String fileName = StandardCharsets.UTF_8.decode(buffer).toString();
                    String destPath = "D:/00_Java_advanced/sandbox/";
                    File directory = new File(destPath);
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }
                    client.fileName = fileName;
                    String fullName = directory.getAbsolutePath() + File.separator
                            + fileName;
                    System.out.println("NIO 传输目标文件：" + fullName);
                    File file = new File(fullName);
                    FileChannel fileChannel = new FileOutputStream(file).getChannel();
                    client.outChannel = fileChannel;
                } else if (client.fileLength == 0) {
                    // 客户端发送过来的，其次是⽂件⻓度
                    long fileLength = buffer.getLong();
                    client.fileLength = fileLength;
                    client.startTime = System.currentTimeMillis();
                    System.out.println("NIO 传输开始：");
                } else {
                    // 客户端发送过来的，最后是⽂件内容，写⼊⽂件内容
                    client.outChannel.write(buffer);
                }
                buffer.clear();
            }
            key.cancel();
        } catch (IOException e) {
            key.cancel();
            e.printStackTrace();
            return;
        }
        // 读取数量-1，表示客户端传输结束标志到了
        if (num == -1) {
            client.outChannel.close();
            System.out.println("上传完毕");
            key.cancel();
            System.out.println("文件接收成功，文件名：" + client.fileName);
            System.out.println("size : " + client.fileLength);
            long endTime = System.currentTimeMillis();
            System.out.println("NIO IO传输毫秒数：" + (endTime - client.startTime));
        }
    }

    public static void main(String[] args) throws IOException {
        NioReceiveServer nioReceiveServer = new NioReceiveServer();
        nioReceiveServer.startServer();
    }


}
