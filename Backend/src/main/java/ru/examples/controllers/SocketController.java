package ru.examples.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import ru.examples.models.Message;
import ru.examples.models.Message.MessageType;
import ru.examples.repositories.MessageRepository;

import static java.lang.String.format;

@Controller
public class SocketController {

    private MessageRepository messageRepo;
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public SocketController(MessageRepository messageRepo, SimpMessageSendingOperations messagingTemplate){
        this.messageRepo = messageRepo;
        this.messagingTemplate = messagingTemplate;
    }

    // 2-nd place where request will process by this method or controller on tag "/hello" like "app/hello"
    @MessageMapping("/hello") // get request from this
    @SendTo("/topic/method") // and send to this
    public void greeting(Message msg) throws InterruptedException {
        System.out.println("got from client");
        messageRepo.save(msg); // successfully stored in the DB
    }

    // send received message from client to this one
    @MessageMapping ("/chat/{roomId}/sendMessage")
    public void sendMessage(@DestinationVariable String roomId, @Payload Message message){
        messageRepo.save(message);
        messagingTemplate.convertAndSend(format("/chat-channel/%s", roomId), message);
    }

    @MessageMapping("/chat/{roomId}/addUser")
    public void addUser(@DestinationVariable String roomId, @Payload Message chatMessage,
                        SimpMessageHeaderAccessor headerAccessor) {
        String currentRoomId = (String) headerAccessor.getSessionAttributes().put("room_id", roomId);
        if (currentRoomId != null) {
            Message leaveMessage = new Message();
            leaveMessage.setType(MessageType.LEAVE);
            leaveMessage.setSender(chatMessage.getSender());
            messagingTemplate.convertAndSend(format("/chat-channel/%s", currentRoomId), leaveMessage);
        }
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        messagingTemplate.convertAndSend(format("/chat-channel/%s", roomId), chatMessage);
    }
}
