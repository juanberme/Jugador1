package com.example.jugador1;

public class Disparo {

    private String type = "Disparo";

    private int vel;
    private int x;
    private int y;

    public void Disparo() {}

    public Disparo(int vel, int x, int y) {
        super();
        this.vel = vel;
        this.x = x;
        this.y = y;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getVel() {
        return vel;
    }
    public void setVel(int vel) {
        this.vel = vel;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

}
