package com.codificador.checkboxrecyclerview;

import java.io.Serializable;

public class Item implements Serializable {

    private boolean selected;
    private int qty;
    private int id;
    private String name;

    public Item(){
        selected = false;
        qty = 0;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}