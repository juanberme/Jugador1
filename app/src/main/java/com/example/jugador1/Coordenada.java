package com.example.jugador1;

public class Coordenada {

    private String type = "Coordenada";

    private int x = 0;
    private int y = 0;

    public Coordenada() {}

    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
