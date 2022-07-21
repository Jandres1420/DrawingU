package co.edu.escuelaing.interactiveblackboard.entities;

import java.util.HashMap;

public class Puntaje {
    HashMap<String,Integer> puntaje;
    private String word;
    private User user;
    public Puntaje(User user, String word){
        puntaje = new HashMap<>();
        this.word = word;
        this.user = user;
    }

    public void setWord(String wordChat){
        if(word.equals(wordChat)){
            
        }
    }


    public HashMap<String,Integer> getPuntaje() {
        return this.puntaje;
    }

    public void setPuntaje(HashMap<String,Integer> puntaje) {
        this.puntaje = puntaje;
    }

    public String getWord() {
        return this.word;
    }


    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
