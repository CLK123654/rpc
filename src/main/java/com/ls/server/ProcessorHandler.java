package com.ls.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessorHandler implements Runnable{
  private Socket socket;
  Object service;

  public ProcessorHandler(Socket socket, Object service) {
    this.socket = socket;
    this.service = service;
  }

  @Override
  public void run() {
    ObjectInputStream in = null;
    ObjectOutputStream out = null;
    try {
     in = new ObjectInputStream(socket.getInputStream());
     RpcRequest rpcRequest = (RpcRequest) in.readObject();
     System.out.println(rpcRequest);
     Object result = invoke(rpcRequest);
      out = new ObjectOutputStream(socket.getOutputStream());
      out.writeObject(result);
      out.flush();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      try {
        out.close();
        in.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private Object invoke(RpcRequest rpcRequest) {
    Object[] parms = rpcRequest.getParms();
    Class<?>[] types = new Class[parms.length];
    for (int i = 0; i < parms.length; i++) {
      types[i] = parms[i].getClass();
    }
    try {
      Method method = service.getClass().getMethod(rpcRequest.getMethodName(), types);
      Object result = method.invoke(service, parms);
      return result;
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    return null;
  }
}
