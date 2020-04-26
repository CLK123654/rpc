package com.ls.client;

import java.io.*;
import java.net.Socket;

public class RpcNetTransport {
  private String host;
  private int port;

  public RpcNetTransport(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public Object transport(RpcRequest rpcRequest) {
    try {
      Socket socket = new Socket(host, port);
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      out.writeObject(rpcRequest);
      out.flush();
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      Object result = in.readObject();
      return  result;
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
}
