package ru.rksp.spiridonov.processor.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "сырые_события_доставки")
public class RawEvent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long идентификатор;
    
    @Column(name = "отправитель")
    private String отправитель;
    
    @Column(name = "получатель")
    private String получатель;
    
    @Column(name = "адрес_доставки")
    private String адресДоставки;
    
    @Column(name = "статус")
    private String статус;
    
    @Column(name = "дата_события")
    private LocalDateTime датаСобытия;

    public RawEvent() {}

    public Long getИдентификатор() { return идентификатор; }
    public void setИдентификатор(Long идентификатор) { this.идентификатор = идентификатор; }

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
}
