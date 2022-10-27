package com.example.be4.models;

public class Item {
    String id;
    String itemName;
    int count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Item(String id, String itemName, int count) {
        this.id = id;
        this.itemName = itemName;
        this.count = count;
    }

    public Item(String itemName, int count) {
        this.itemName = itemName;
        this.count = count;
    }

    public Item(){

    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
