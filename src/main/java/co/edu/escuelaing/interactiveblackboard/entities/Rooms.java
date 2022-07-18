package co.edu.escuelaing.interactiveblackboard.entities;

public class Rooms {
    private String name;
    private Board board;
    public Rooms(String name){
        this.name = name;
        board = new Board();
    }



    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Board getBoard() {
        return this.board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

}
