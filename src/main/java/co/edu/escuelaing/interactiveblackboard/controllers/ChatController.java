package co.edu.escuelaing.interactiveblackboard.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import co.edu.escuelaing.interactiveblackboard.entities.Message;

@Controller
public class ChatController {

    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public Message register(@Payload Message chatMessage,SimpMessageHeaderAccessor headerAccesor){
        headerAccesor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message chatMessage ){
        return chatMessage;
    }

}
