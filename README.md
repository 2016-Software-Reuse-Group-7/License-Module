# License Module

许可证模块

## Usage

```java
本模块提供接口:

public interface License {
    void setLicenseTypename(String typeName);    // 设置license名称
    String getLicenseTypename();                 // 获取license名称
    void onReceiveNewRequest();                  // 当server端接收到消息后请调用

    boolean checkThroughput();                   // 检查吞吐量是否超出上限
    boolean checkCapacity();                     // 检查消息发送量是否超出上限

    void setMaxThroughput(int maxThroughput) throws InvalidLicenseNumberException;             // 设置最大吞吐量
    void setMaxCapacity(int maxCapacity) throws InvalidLicenseNumberException;                 // 设置最大消息发送量
    void setClearPeriodSeconds(double clearPeriodSecond) throws InvalidLicenseNumberException; // 设置吞吐量清零时间
}

```

