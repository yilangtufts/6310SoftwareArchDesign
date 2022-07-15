package edu.gatech.cs6310;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String store;
    private String orderID;
    private int droneId;
    //private String item;
    private String customer;
    //private Double totalWeight;
    private List<Entry> listOfItem;
    //start_order,kroger,purchaseA,1,aapple2
    //Order(tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[2]);

    public Order(String store, String orderID, int droneId, String customer) {
        this.store = store;
        this.orderID = orderID;
        this.droneId = droneId;
        //this.item = item;
        this.customer = customer;
        listOfItem = new ArrayList<>();
        //this.totalWeight = totalWeight;
    }

    //request_item,kroger,purchaseA,pot_roast,3,9

    public String getOrderID() {
        return orderID;
    }

    public int getDroneId(){
        return droneId;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

//    @Override
//    public String toString() {
//        //display
//        return "orderID:" + getOrderID();
//    }

    public void requestItem(Entry entry){
        listOfItem.add(entry);
    }

    public String getCustomer(){
        return this.customer;
    }


    public void displayOrder(){
        System.out.println("orderID:" + this.orderID);
        for(Entry entry: this.listOfItem){
            System.out.println(entry);
        }

    }

    public int getTotalCost(){
        int total = 0;
        for(Entry entry: this.listOfItem){
            total += entry.getTotalCost();
        }
        return total;
    }

    public int getTotalWeight(){
        int total = 0;
        for(Entry entry: listOfItem){
            total += entry.getTotalWeight();
        }
        return total;
    }

    public boolean containsItem(String itemName){
        for(Entry entry: listOfItem){
            if(itemName.equals(entry.getItemName())){
                return true;
            }
        }
        return false;
    }
//
//    public Double getTotalWeight() {
//        return totalWeight;
//    }
//
//    public void setTotalWeight(Double totalWeight) {
//        this.totalWeight = totalWeight;
//    }

}
