package edu.gatech.cs6310;

import java.util.HashSet;
import java.util.Set;

public class Drone {

    private String store;
    private int droneID;
    private int totalCapacity;
    private int RemainingCapacity;
    private int tripsLeft;
    private String flownBy;
    private String flownByPilotId;
    private Set<String> orders;

    public Drone(String store, Integer droneID, Integer totalCapacity, Integer tripsLeft) {
        this.store = store;
        this.droneID = droneID;
        this.totalCapacity = totalCapacity;
        this.tripsLeft = tripsLeft;
        orders = new HashSet<>();
        this.RemainingCapacity = totalCapacity;
        flownBy = null;
        flownByPilotId = null;

    }



    public Integer getDroneID() {
        return droneID;
    }

    public void setDroneID(Integer droneID) {
        this.droneID = droneID;
    }

    public Integer getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(Integer totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public Integer getNumberOfOrders() {
        return orders.size();
    }

    public Integer getRemainingCapacity() {
        return RemainingCapacity;
    }

    public void setRemainingCapacity(Integer remainingCapacity) {
        RemainingCapacity = remainingCapacity;
    }

    public Integer getTripsLeft() {
        return tripsLeft;
    }

    public void setTripsLeft(Integer tripsLeft) {
        this.tripsLeft = tripsLeft;
    }

    public void assignPilot(Pilot pilot){
        flownBy = pilot.getName();
        flownByPilotId = pilot.getPilotAccount();
    }

    public void resignPilot(){
        flownBy = null;
        flownByPilotId = null;
    }

    public void addOrder(String orderId){
        orders.add(orderId);
    }

    public void removeOrder(String orderId){
        orders.remove(orderId);
    }

    public String getPilotID(){
        return this.flownByPilotId;
    }

    //droneID:1,total_cap:40,num_orders:0,remaining_cap:40,trips_left:3

    @Override
    public String toString() {
        //display
        String str = "droneID:" + getDroneID() + "," + "total_cap:" + getTotalCapacity() + "," + "num_orders:" + getNumberOfOrders() + "," + "remaining_cap:" + getRemainingCapacity() + "," + "trips_left:" + getTripsLeft();
        if(flownBy != null){
            str = str + ",flown_by:" + flownBy;
        }
        return str;

    }

}
