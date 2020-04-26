package com.ls.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcServerProxy {

  ExecutorService executorService = Executors.newCachedThreadPool();

  public void publisher(Object service, int port) {
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(port);
      while (true) {
        System.out.println("服务器监听。。。。。");
        Socket socket = serverSocket.accept();
        System.out.println("一个客户端连接了。。。。" + socket.getLocalAddress().getHostName());
        executorService.submit(new ProcessorHandler(socket, service));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
