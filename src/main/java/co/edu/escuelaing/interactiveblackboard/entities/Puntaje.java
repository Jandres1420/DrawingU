package co.edu.escuelaing.interactiveblackboard.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Puntaje {
    HashMap<String,String> puntaje;
    private List<User> users;
    private User user;
    public Puntaje(){
        puntaje = new HashMap<>();
        users = new ArrayList<>();
    }

    public int setWord(String wordChat, User user, String word){
        int memoria = 0;
        int pos = 0;
        System.out.println(" Entra setWord");
        if(!(users.contains(user))){
            System.out.println("no esta contenido");
            users.add(user);
            añadirPersonas();
        }
        if(word.equals(wordChat)){
            System.out.println("Es la misma palabra");
            pos = users.indexOf(user);   
            users.get(pos).setPuntaje(puntajeCantidad(user)); 
        }
        return pos;
    }

    private void añadirPersonas(){
        System.out.println("Añade al hashmap");
        for(int i= 0 ; i<users.size();i++){
            puntaje.put(users.get(i).getName(), "nada");
        }
        System.out.println("HashMap size : "+puntaje.size());
    }

    private int puntajeCantidad(User user){
        int pos = users.indexOf(user);
        System.out.println("posicion en lista " + pos);
        int pun = users.get(pos).getPuntaje();
        if(users.size()>=3){
            System.out.println("Puntuación del usuario " + user.getName() + " puntos " + user.getPuntaje());
            if (puntaje.get(user.getName()).equals("nada")) {
                for (int i = 0; i < puntaje.values().size(); i++) {
                    int numeroNada = 0;
                    List<String> list = new ArrayList<String>(puntaje.values());
                    if (list.get(i).equals("puntos")) {
                        numeroNada++;
                    } else {
                        numeroNada--;
                    }
                    pun -= numeroNada;
                }
            }
            puntaje.put(user.getName(), "puntos");
        }      
        return pun;
    }


    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> user) {
        this.users = user;
    }

    public HashMap<String,String> getPuntaje() {
        return this.puntaje;
    }

    public void setPuntaje(HashMap<String,String> puntaje) {
        this.puntaje = puntaje;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
        users.add(user); 
    }
}
