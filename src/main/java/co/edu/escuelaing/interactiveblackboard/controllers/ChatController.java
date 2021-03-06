package co.edu.escuelaing.interactiveblackboard.controllers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import co.edu.escuelaing.interactiveblackboard.entities.ChatMessage;
import co.edu.escuelaing.interactiveblackboard.entities.DrawingU;
import co.edu.escuelaing.interactiveblackboard.entities.User;



@Controller
public class ChatController { 

    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println();
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        DrawingU.getInstance().getRooms().getPuntaje().setUser(new User(chatMessage.getSender()
        ));
        return chatMessage;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        System.out.println("este es el contenido del mensaje " + chatMessage.getContent());
        System.out.println("esta es la persona que envia el mensaje " + chatMessage.getSender());
        System.out.println(DrawingU.getInstance().getRooms().getPuntaje().setWord(chatMessage.getContent(), chatMessage.getSender()));
        return chatMessage;
    }

}
