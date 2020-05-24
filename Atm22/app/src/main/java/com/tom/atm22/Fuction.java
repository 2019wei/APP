package com.tom.atm22;

public class Fuction {
    String name;
    int icon;

    public Fuction() {
    }

    public Fuction(String name) {
        this.name = name;
    }

    public Fuction(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
