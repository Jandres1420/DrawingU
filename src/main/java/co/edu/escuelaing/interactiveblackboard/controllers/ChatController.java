package co.edu.escuelaing.interactiveblackboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import co.edu.escuelaing.interactiveblackboard.entities.Message;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate SimpMessagingTemplate;
    // Un broadcast para que las demás personas lean el mensaje 
    @MessageMapping("/message") //app/message "this url"
    @SendTo("chatroom/public")
    private Message receivePublicMessage(@Payload Message message){
        return message;
    }

    @MessageMapping("private-message")
    public Message receiverPrivateMessage(@Payload Message message){
        return message;
    }

}
