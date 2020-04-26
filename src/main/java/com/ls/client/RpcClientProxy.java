package com.ls.client;

import java.lang.reflect.Proxy;

public class RpcClientProxy {
  public static <T> T clientProxy(Class<T> interfaceCls, String host, int port) {
    return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),
            new Class<?>[]{interfaceCls}, new RemoteInvocationHandler(host, port));
  }
}
