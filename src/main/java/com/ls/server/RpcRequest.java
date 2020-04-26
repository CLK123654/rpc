package com.ls.server;

import java.io.Serializable;

public class RpcRequest implements Serializable {
  private String className;
  private String methodName;
  private Object[] parms;

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public Object[] getParms() {
    return parms;
  }

  public void setParms(Object[] parms) {
    this.parms = parms;
  }
}
