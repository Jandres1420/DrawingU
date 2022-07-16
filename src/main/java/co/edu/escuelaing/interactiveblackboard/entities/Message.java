package co.edu.escuelaing.interactiveblackboard.entities;
import co.edu.escuelaing.interactiveblackboard.entities.Status;


public class Message {
    
    private String content, sender;
    private Status status;
    private Status type;
    

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public Status getType() {
        return this.type;
    }

    public void setType(Status type) {
        this.type = type;
    }
    
}
