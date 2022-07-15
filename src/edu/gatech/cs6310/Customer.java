package edu.gatech.cs6310;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer {

    //name:Alana_Apple,phone:222-222-2222,rating:4,credit:100
    private String customerAccount;
    private String name;
    private String phone;
    private Integer rating;
    private Integer credit;
    private Integer pendingCredit;
    //private List<Order>listOfCompletedOrders;
    //private List<Order>listOfPendingOrders;
    //private Double totalCostOfOrder;

    private Map<String, Order> orderMap;

    public Customer(String customerAccount, String lastName, String firstName, String phone, Integer rating, Integer credit) {
        this.customerAccount = customerAccount;
        this.name = lastName+"_"+firstName;
        this.phone = phone;
        this.rating = rating;
        this.credit = credit;
        pendingCredit = 0;
        orderMap = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public void reduceCredit(int paid){
        int newCredit = getCredit() - paid;
        setCredit(newCredit);
    }
    public int getPendingCredit(){return this.pendingCredit;}
    public void setPendingCredit(Integer pendingCredit) { this.pendingCredit = pendingCredit;}

    @Override
    public String toString() {
        //display
        return "name:" + getName() + "," + "phone:" + getPhone() + "," + "rating:" + getRating() + "," + "credit:" + getCredit();
        //name:Alana_Apple,phone:222-222-2222,rating:4,credit:100
    }

}
