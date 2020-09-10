package ru.examples.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;
import ru.examples.models.HelloMsg;
import ru.examples.models.WebMsg;

//@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class SocketController {
    @MessageMapping()
    public void isListened(){
        System.out.println("YEEEEEAHH");
    }

    // 2-nd place where request will process by this method or controller on tag "/hello" like "app/hello"
    @MessageMapping("/hello") // get request from this
    @SendTo("/topic/method") // and send to this
    public WebMsg greeting(HelloMsg msg) throws InterruptedException {
        System.out.println("got from client");
        return new WebMsg("Hello, " + HtmlUtils.htmlEscape(msg.getText()));
    }

}
