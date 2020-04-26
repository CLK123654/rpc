package com.ls.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvocationHandler implements InvocationHandler {

  private String host;
  private int port;

  public RemoteInvocationHandler(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    RpcRequest rpcRequest = new RpcRequest();
    rpcRequest.setMethodName(method.getName());
    rpcRequest.setParms(args);
    RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);
    Object ret = rpcNetTransport.transport(rpcRequest);
    System.out.println(ret);
    return ret;
  }
}
