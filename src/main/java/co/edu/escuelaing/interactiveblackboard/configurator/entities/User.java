package co.edu.escuelaing.interactiveblackboard.configurator.entities;

public class User {
    private String name;
    public User(String name){
        this.name = name;
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

}
