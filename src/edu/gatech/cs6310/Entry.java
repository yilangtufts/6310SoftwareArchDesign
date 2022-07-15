package edu.gatech.cs6310;

public class Entry {
    private String store;
    private String itemName;
    private int quantity;
    private int unitPrice;
    private int unitWeight;
    private int totalPrice;
    private int totalWeight;


    public Entry(String store, String itemName, int quantity, int unitPrice, int unitWeight) {
        this.store = store;
        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.unitWeight = unitWeight;
        this.totalPrice = getTotalCost();
        this.totalWeight = getTotalWeight();
    }

    public int getTotalCost(){
        return unitPrice * quantity;
    }

    public int getTotalWeight(){
        return unitWeight * quantity;
    }

    public String getItemName(){return itemName;}

    @Override
    public String toString() {
        return "item_name:" + itemName +
                ",total_quantity:" + quantity +
                ",total_cost:" + getTotalCost() +
                ",total_weight:" + getTotalWeight();
    }
}
