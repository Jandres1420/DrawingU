package co.edu.escuelaing.interactiveblackboard.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Rooms {

    private HashMap<String, List<User>> salasDef;
    private Board board;

    public Rooms() {
        board = new Board();
        defsalas();
    }

    public Board getBoard() {
        return this.board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    private void defsalas() {
        List<User> list = new ArrayList<>();
        salasDef = new HashMap<>();
        salasDef.put("Sala A", list);
        salasDef.put("Sala B", list);
        salasDef.put("Sala C", list);
        salasDef.put("Sala D", list);

    }

    public void settingUser(User user, String key) {
        List<User> list = new ArrayList<>(salasDef.get(key));
        list.add(user);
        salasDef.put(key, list); 
        for (User u : list) {
            try {
                System.out.println("llave " + key);
                System.out.println("entro al for");
                System.out.println(u.getName());
            } catch (Exception e) {
                System.out.println("entro a error ");
            }
        }
        settingPintor(user, key);
    }

    public int gettingSize(String name) {
        return salasDef.get(name).size();
    }

    public HashMap<String, List<User>> getSalasDef() {
        return this.salasDef;
    }

    public void setSalasDef(HashMap<String, List<User>> salasDef) {
        this.salasDef = salasDef;
    }

    public void settingPintor(User user, String key ) {
        List<User> users = new ArrayList<>(salasDef.get(key));
            if (users.size() == 1 && users.contains(user)) {
                System.out.println("Es pintor");
                users.forEach(x -> x.setPintor(true));
            } else {
                System.out.println(" NOOO Es pintor");
                int pos = users.indexOf(user);
                users.get(pos).setPintor(false);
            }
            System.out.println("Usuario agregado: " + user.getName());
    }

}
