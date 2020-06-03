package com.example.tictactoe;

public class Player {
    int[] clicks;
    private int id;
    private boolean flag;

    public Player(int id) {
        this.id = id;
        flag = false;
        clicks = new int[5];
    }

    public int getId() {
        return id;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Player " + id;
    }

}
