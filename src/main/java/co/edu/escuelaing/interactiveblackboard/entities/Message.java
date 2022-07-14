package co.edu.escuelaing.interactiveblackboard.entities;
import co.edu.escuelaing.interactiveblackboard.entities.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {
    
    private String senderName, receiverName,message;

    private Status status;
    
 
}
