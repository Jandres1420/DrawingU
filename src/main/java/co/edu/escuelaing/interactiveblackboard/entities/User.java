package co.edu.escuelaing.interactiveblackboard.entities;

public class User {
    private String name;
    private boolean pintor;
 
   private int puntaje; 
    public User(String name){
        this.name = name;
        puntaje = 0;
    }

    

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public boolean isPintor() {
        return this.pintor;
    }

    public boolean getPintor() {
        return this.pintor;
    }

    public void setPintor(boolean pintor) {
        this.pintor = pintor;
    }


    public int getPuntaje() {
        return this.puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }


}
