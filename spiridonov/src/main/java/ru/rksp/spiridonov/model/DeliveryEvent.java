package ru.rksp.spiridonov.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DeliveryEvent implements Serializable {
    private String отправитель;
    private String получатель;
    private String адресДоставки;
    private String статус;
    private LocalDateTime датаСобытия;

    public DeliveryEvent() {}

    public String getОтправитель() { return отправитель; }
    public void setОтправитель(String отправитель) { this.отправитель = отправитель; }

    public String getПолучатель() { return получатель; }
    public void setПолучатель(String получатель) { this.получатель = получатель; }

    public String getАдресДоставки() { return адресДоставки; }
    public void setАдресДоставки(String адресДоставки) { this.адресДоставки = адресДоставки; }

    public String getСтатус() { return статус; }
    public void setСтатус(String статус) { this.статус = статус; }

    public LocalDateTime getДатаСобытия() { return датаСобытия; }
    public void setДатаСобытия(LocalDateTime датаСобытия) { this.датаСобытия = датаСобытия; }

    @Override
    public String toString() {
        return "DeliveryEvent{" +
                "отправитель='" + отправитель + '\'' +
                ", получатель='" + получатель + '\'' +
                ", адресДоставки='" + адресДоставки + '\'' +
                ", статус='" + статус + '\'' +
                ", датаСобытия=" + датаСобытия +
                '}';
    }
}