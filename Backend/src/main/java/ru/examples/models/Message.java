package ru.examples.models;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


// This class also unnecessary for application work, it was created beforehand
// It represents message entity in DB
@Entity
@Table(name = "messages")
public class Message {

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "content")
    private String content;

    @Column(name = "sender")
    private String sender;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime timestamp;

    @Column(name = "room_ID")
    private long roomID;

    @Column(name = "type")
    private MessageType type;


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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getRoomID() {
        return roomID;
    }

    public void setRoomID(long roomID) {
        this.roomID = roomID;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
