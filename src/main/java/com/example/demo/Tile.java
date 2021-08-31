package com.example.demo;

public class Tile {
    private boolean isMine = false;
    private boolean coverStatus = true;
    int number = 0;

    public boolean getMine() {
        return isMine;
    }
    public void setMine(boolean newIsMine) {
        this.isMine = newIsMine;
    }
    public boolean getCoverStatus() {
        return coverStatus;
    }
    public void setCoverStatus(boolean newCoverStatus) {
        this.coverStatus = newCoverStatus;
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int newNumber) {
        this.number = newNumber;
    }
}
