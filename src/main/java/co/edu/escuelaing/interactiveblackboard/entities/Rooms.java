package co.edu.escuelaing.interactiveblackboard.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Rooms {

    private HashMap<String,List<String>> salasDef;
    private Board board;
    public Rooms(){
        board = new Board();
        defsalas();
    }


    public Board getBoard() {
        return this.board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    private void defsalas(){
        List<String>list = new ArrayList<>();
        salasDef = new HashMap<>();
        salasDef.put("Sala A", list);
        salasDef.put("Sala B", list);
        salasDef.put("Sala C", list);
        salasDef.put("Sala D", list);

    }

    public void settingUser(User user,String key){
        List<String> list = salasDef.get(key);
        list.add(user.getName());
    }

    public int gettingSize(String name){
        return salasDef.get(name).size();
    }


    public HashMap<String,List<String>> getSalasDef() {
        return this.salasDef;
    }

    public void setSalasDef(HashMap<String,List<String>> salasDef) {
        this.salasDef = salasDef;
    }

}
