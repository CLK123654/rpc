package com.ls.client;

public class ClientCustomer {
  public static void main(String[] args) {
    HelloService service = RpcClientProxy.clientProxy(HelloService.class, "127.0.0.1", 6666);
    String ret = service.sayHello("饕鬄");
    System.out.println(ret);
  }
}

