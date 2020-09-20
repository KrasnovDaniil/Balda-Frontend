package ru.examples.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.examples.models.Message;
import ru.examples.repositories.MessageRepository;

@Controller
public class SocketController {

    @Autowired
    MessageRepository messageRepo;
    // 2-nd place where request will process by this method or controller on tag "/hello" like "app/hello"
    @MessageMapping("/hello") // get request from this
    @SendTo("/topic/method") // and send to this
    public void greeting(Message msg) throws InterruptedException {
        System.out.println("got from client");
        messageRepo.save(msg); // successfully stored in the DB
    }

}
