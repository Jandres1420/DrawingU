package co.edu.escuelaing.interactiveblackboard.configurator.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private List<User> users;
    private List<Color> colors;
    private Words words;
    private static  Board _instance = new Board();
    public static Board getInstance(){
        return _instance;
    }
    public Board(){
        users = new ArrayList<User>();
        colors = new ArrayList<Color>();
        words = new Words();
    }

    public List<User> getUsuarios() {
        return users;
    }
    public void setUsuario( User user) {
        if(!(users.contains(user))){
            users.add(user);
            System.out.println("Usuario agregado: " + user.getName());
        }
    }


    public List<Color> getColores() {
        return colors;
    }

    public void setColores(List<Color> colores) {
        this.colors = colores;
    }

    public Words getWords() {
        return words;
    }

    public void setWords(Words words) {
        this.words = words;
    }
}
