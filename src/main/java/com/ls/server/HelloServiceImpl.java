package com.ls.server;

public class HelloServiceImpl implements HelloService {
  @Override
  public String sayHello(String msg) {
    return "hello," + msg;
  }
}
