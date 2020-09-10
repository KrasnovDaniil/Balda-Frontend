package ru.examples.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@RestController
@RequestMapping("message/")
public class MessageController {

//    @Autowired
//    private WebSocketStompClient stompClient;

    @GetMapping("get")
    public String getMsgs(){
        return "It works";
    }


}
