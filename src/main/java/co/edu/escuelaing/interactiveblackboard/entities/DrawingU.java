package co.edu.escuelaing.interactiveblackboard.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DrawingU {
    private Rooms rooms;
    private static DrawingU _instance = new DrawingU();
    public static DrawingU getInstance() {
        return _instance;
    }
    public DrawingU(){
        rooms = new Rooms();
    }

    public Rooms getRooms() {
        return this.rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }
    

    @Override
    public String toString() {
        return "DrawingU [rooms=" + rooms + "]";
    }


}
