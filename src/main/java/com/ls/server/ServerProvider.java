package com.ls.server;

public class ServerProvider {
  public static void main(String[] args) {
    RpcServerProxy proxy = new RpcServerProxy();
    HelloService service = new HelloServiceImpl();
    proxy.publisher(service, 6666 );
  }
}
