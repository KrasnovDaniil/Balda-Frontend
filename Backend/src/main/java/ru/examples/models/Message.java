package ru.examples.models;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "content")
    private String content;

//    @OneToMany
//    @Column(name = "sender")
    private String sender;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime timestamp;

    @Column(name = "Room_ID")
    private long roomID;



    public Message(){}

    public Message(String content, String sender, long roomID) {
        this.content = content;
        this.sender = sender;
        this.roomID = roomID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
