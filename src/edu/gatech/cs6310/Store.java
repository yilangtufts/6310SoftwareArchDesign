package edu.gatech.cs6310;

import java.util.*;

public class Store {
    private String storeName;
    private Integer revenue;

    private List<Item> itemList;
    private TreeMap<String, Item> itemMap;
    //private Map<String, Pilot> pilotMap;
    private Map<Integer, Drone> droneMap;
    private TreeMap<String, Order> orderMap;
    private TreeMap<String, Order> completedOrder;
    private Map<String, Integer> pilotToDrone;
    private Map<String, Integer> orderToDrone;

    public Store(String storeName, Integer revenue) {
        this.storeName = storeName;
        this.revenue = revenue;
        itemList = new ArrayList<>();
        itemMap = new TreeMap<>();
        //pilotMap = new HashMap<>();
        droneMap = new HashMap<>();
        orderMap = new TreeMap<>();
        completedOrder = new TreeMap<>();
        pilotToDrone = new HashMap<>();
        orderToDrone = new HashMap<>();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public boolean addItem(Item item){
        if(itemMap.containsKey(item.getItemName())){
            return false;
        }
        itemMap.put(item.getItemName(), item);
        return true;
        //System.out.println("OK:change_completed");
    }

    public boolean addOrder(Order order) {

        orderMap.put(order.getOrderID(), order);
        Drone dronePicked = droneMap.get(order.getDroneId());
//        for(Drone drone: droneMap.values()){
//            if(drone.isAvailable(order)){
//                dronePicked = drone;
//                break;
//            }
//        }
        if(dronePicked == null){
            System.out.println("ERROR:drone_identifier_does_not_exist");
            return false;
        }
        // if(dronePicked != null){
        dronePicked.addOrder(order.getOrderID());
        orderToDrone.put(order.getOrderID(), dronePicked.getDroneID());
        return true;
        // }
    }

    public boolean containsOrder(String orderId){
        return orderMap.containsKey(orderId);
    }

    public Order getOrder(String orderId){
        return orderMap.get(orderId);
    }

    public Order cancelOrder(String orderId){
        Order removed = orderMap.remove(orderId);
        orderMap.remove(orderId);
        String removedOrderID = removed.getOrderID();
        //if(orderToDrone.containsKey(removedOrderID)){
            Drone drone = droneMap.get(orderToDrone.get(removedOrderID));
            drone.removeOrder(removedOrderID);
            drone.setRemainingCapacity(drone.getRemainingCapacity() + removed.getTotalWeight());
        //}
        return removed;
    }

    public Item getItem(String name){
        return itemMap.get(name);
    }

    public void displayAllItem(){
        for(String name: itemMap.keySet()){
            System.out.println(itemMap.get(name));
        }
    }

    public void displayAllOrders(){
        for(Order order: orderMap.values()){
            order.displayOrder();
        }
    }

    public void addDrone(Drone drone){
        int droneId = drone.getDroneID();
        droneMap.put(droneId, drone);
    }

    public void removeDrone(int droneId){
        droneMap.remove(droneId);
    }

    public Drone getDrone(int droneId){
        return droneMap.get(droneId);
    }

//    public void addPilot(Pilot pilot){
//
//        String pilotId = pilot.getPilotAccount();
//        pilotMap.put("1", pilot);
//    }
//
//    public void removePilot(){
//
//    }

    public void assignPilotToDrone(Pilot pilot, int droneId){
        //Pilot pilot = pilotMap.get(pilotId);
        Drone drone = droneMap.get(droneId);
        if(drone == null){
            return;
        }
        if(pilotToDrone.containsKey(pilot.getPilotAccount())){
            Drone prevDrone = droneMap.get(pilotToDrone.get(pilot.getPilotAccount()));
            prevDrone.resignPilot();
        }
        drone.assignPilot(pilot);
        pilotToDrone.put(pilot.getPilotAccount(), droneId);
    }

//    public void displayAllPilots(){
//        for(String pilotId: pilotMap.keySet()){
//            Pilot pilot = pilotMap.get(pilotId);
//            System.out.println(pilot);
//        }
//    }

    public void displayAllDrones(){
        for(int droneId: droneMap.keySet()){
            System.out.println(droneMap.get(droneId));
        }
    }

    public String processPurchase(String orderId){
        Order order = orderMap.get(orderId);
        int newRevenue = getRevenue() + order.getTotalCost();
        setRevenue(newRevenue);
        orderMap.remove(orderId);
        completedOrder.put(orderId, order);
        Drone drone = droneMap.get(orderToDrone.get(orderId));
        drone.setRemainingCapacity(drone.getRemainingCapacity() + order.getTotalWeight());
        drone.setTripsLeft(drone.getTripsLeft() - 1);
        drone.removeOrder(orderId);
        String pilotId = drone.getPilotID();
        return pilotId;
    }

    @Override
    public String toString() {
        //display
        return "name:" + getStoreName() +"," + "revenue:" + getRevenue();
    }

//    public static void main(String[] args){
//        Store s = new Store("yilang", 9999999);
//        System.out.println(s);
//    }

}
