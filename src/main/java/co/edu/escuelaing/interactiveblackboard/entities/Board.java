package co.edu.escuelaing.interactiveblackboard.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private List<User> users;
    private List<Color> colors;
    private List<Rooms> rooms;
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
            if(users.size() == 0){
                System.out.println("Es pintor");
                users.add(user);
                users.forEach(x -> x.setPintor(true));
            }else{
                System.out.println(" NOOO Es pintor");
                users.add(user);
                int pos = users.indexOf(user);
                users.get(pos).setPintor(false);
            }
            System.out.println("Usuario agregado: " + user.getName());
        }
        
    }

    public int getPositionUser(String user){
        int pos = 0;
        for(int i = 0 ; i<users.size();i++){
            if(users.get(i).getName() == user){
                pos = i;
            }
        }
        return pos;
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
