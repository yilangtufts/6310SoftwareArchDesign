package edu.gatech.cs6310;

public class Item {

    private String itemName;
    private int weight;

    public Item(String itemName, int weight) {
        this.itemName = itemName;
        this.weight = weight;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return getItemName()+","+getWeight();
    }
}

