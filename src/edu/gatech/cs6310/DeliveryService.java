package edu.gatech.cs6310;
import java.util.*;

public class DeliveryService {

    //all data
    //Store store;
    TreeMap<String, Store> storeMap;
    TreeMap<String, Pilot> pilotMap;
    HashMap<String, Drone> droneMap;
    TreeMap<String, Customer> customerMap;
    Set<String> pilotLicenceSet;
    //TreeMap<String, Order> orderMap;
    //initialization

        //store = new Store();
    public DeliveryService(){
        pilotMap = new TreeMap<>();
        storeMap = new TreeMap<>();
        droneMap = new HashMap<>();
        customerMap = new TreeMap<>();
        pilotLicenceSet = new HashSet<>();
       // orderMap = new TreeMap<>();
    }

    private Store getStore(String name){
        return storeMap.get(name);
    }


    public void commandLoop() {
        Scanner commandLineInput = new Scanner(System.in);
        String wholeInputLine;
        String[] tokens;
        final String DELIMITER = ",";

        while (true) {
            try {
                // Determine the next command and echo it to the monitor for testing purposes

                wholeInputLine = commandLineInput.nextLine();
                if(wholeInputLine.startsWith("//")){
                    System.out.println("> " + wholeInputLine);
                    continue;
                }
                tokens = wholeInputLine.split(DELIMITER);
                System.out.println("> " + wholeInputLine);

                if (tokens[0].equals("make_store")) {
                    if(storeMap.containsKey(tokens[1])){
                        System.out.println("ERROR:store_identifier_already_exists");
                        continue;
                    }
                    Store store = new Store(tokens[1], Integer.parseInt(tokens[2]));
                    storeMap.put(tokens[1], store);
                    System.out.println("OK:change_completed");

                } else if (tokens[0].equals("display_stores")) {
                    for(Store store: storeMap.values()){
                        System.out.println(store);
                    }
                    System.out.println("OK:display_completed");


                } else if (tokens[0].equals("sell_item")) {
                    Store store = getStore(tokens[1]);
                    if(store == null){
                        System.out.println("ERROR:store_identifier_does_not_exist");
                        continue;
                    }
                    Item item = new Item(tokens[2], Integer.parseInt(tokens[3]));
                    //store.addItem(item);
                    System.out.println(store.addItem(item)? "OK:change_completed": "ERROR:item_identifier_already_exists");
                    //System.out.println("store: " + tokens[1] + ", item: " + tokens[2] + ", weight: " + tokens[3]);

                } else if (tokens[0].equals("display_items")) {
                    Store store = getStore(tokens[1]);
                    if(store == null){
                        System.out.println("ERROR:store_identifier_does_not_exist");
                        continue;
                    }
                    store.displayAllItem();
                    System.out.println("OK:display_completed");

                } else if (tokens[0].equals("make_pilot")) {
                    if(pilotMap.containsKey(tokens[1])){
                        System.out.println("ERROR:pilot_identifier_already_exists");
                        continue;
                    }
                    if(pilotLicenceSet.contains(tokens[6])){
                        System.out.println("ERROR:pilot_license_already_exists");
                        continue;
                    }


                    Pilot pilot = new Pilot(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], Integer.parseInt(tokens[7]));
                    pilotMap.put(tokens[1], pilot);
                    pilotLicenceSet.add(tokens[6]);
                    System.out.println("OK:change_completed");
                    //System.out.print("account: " + tokens[1] + ", first_name: " + tokens[2] + ", last_name: " + tokens[3]);
                    //System.out.println(", phone: " + tokens[4] + ", tax: " + tokens[5] + ", license: " + tokens[6] + ", experience: " + tokens[7]);

                } else if (tokens[0].equals("display_pilots")) {
                    for(Pilot pilot: pilotMap.values()){
                        System.out.println(pilot);
                    }
                    System.out.println("OK:display_completed");
                    //System.out.println("no parameters needed");

                } else if (tokens[0].equals("make_drone")) {
                    Drone drone = new Drone(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
                    if(droneMap.containsKey(tokens[1] + drone.getDroneID())){
                        System.out.println("ERROR:drone_identifier_already_exists");
                        continue;
                    }

                    droneMap.put(tokens[1] + drone.getDroneID(), drone);
                    //System.out.println("888");
                    Store store = getStore(tokens[1]);
                    if(store == null){
                        System.out.println("ERROR:store_identifier_does_not_exist");
                        continue;
                    }
                    store.addDrone(drone);
                    System.out.println("OK:change_completed");
                    //System.out.println("store: " + tokens[1] + ", drone: " + tokens[2] + ", capacity: " + tokens[3] + ", fuel: " + tokens[4]);

                } else if (tokens[0].equals("display_drones")) {
                    Store store = getStore(tokens[1]);
                    if(store == null){
                        System.out.println("ERROR:store_identifier_does_not_exist");
                        continue;
                    }
                    store.displayAllDrones();
                    System.out.println("OK:display_completed");
                    //System.out.println("store: " + tokens[1]);

                } else if (tokens[0].equals("fly_drone")) {
                    Store store = getStore(tokens[1]);
                    if(store == null){
                        System.out.println("ERROR:store_identifier_does_not_exist");
                        continue;
                    }
                    Pilot pilot = pilotMap.get(tokens[3]);
                    if(pilot == null){
                        System.out.println("ERROR:pilot_identifier_does_not_exist");
                        continue;
                    }
                    //String pilotName = pilot.getName();
                    if(!droneMap.containsKey(tokens[1]+Integer.parseInt(tokens[2]))){
                        System.out.println("ERROR:drone_identifier_does_not_exist");
                        continue;
                    }
                    store.assignPilotToDrone(pilot, Integer.parseInt(tokens[2]));
                    //pilot.gainExperience();
                    System.out.println("OK:change_completed");
                    //System.out.println("store: " + tokens[1] + ", drone: " + tokens[2] + ", pilot: " + tokens[3]);

                } else if (tokens[0].equals("make_customer")) {
                    if(customerMap.containsKey(tokens[1])){
                        System.out.println("ERROR:customer_identifier_already_exists");
                        continue;
                    }
                    Customer customer = new Customer(tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]));

                    customerMap.put(tokens[1], customer);
                    //make_customer,aapple2,Alana,Apple,222-222-2222,4,100
                    System.out.println("OK:change_completed");

                    //System.out.print("account: " + tokens[1] + ", first_name: " + tokens[2] + ", last_name: " + tokens[3]);
                    //System.out.println(", phone: " + tokens[4] + ", rating: " + tokens[5] + ", credit: " + tokens[6]);

                } else if (tokens[0].equals("display_customers")) {
                    for(Customer customer: customerMap.values()){
                        System.out.println(customer);
                    }
                    System.out.println("OK:display_completed");
                    //System.out.println("no parameters needed");

                } else if (tokens[0].equals("start_order")) {
                    //start_order,kroger,purchaseA,1,aapple2
                    Store store = storeMap.get(tokens[1]);
                    if(store == null){
                        System.out.println("ERROR:store_identifier_does_not_exist");
                        continue;
                    }
                    Order order = new Order(tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4]);
                    if(store.containsOrder(order.getOrderID())){
                        System.out.println("ERROR:order_identifier_already_exists");
                        continue;
                    }
                    if(store.getDrone(Integer.parseInt(tokens[3])) == null){
                        System.out.println("ERROR:drone_identifier_does_not_exist");
                        continue;
                    }



                    if(!customerMap.containsKey(tokens[4])){
                        System.out.println("ERROR:customer_identifier_does_not_exist");
                        continue;
                    }


                    //orderMap.put(tokens[2], order);
                    boolean addStatus = store.addOrder(order);
                    if(addStatus){System.out.println("OK:change_completed");}

                    //System.out.println("store: " + tokens[1] + ", order: " + tokens[2] + ", drone: " + tokens[3] + ", customer: " + tokens[4]);

                } else if (tokens[0].equals("display_orders")) {
                    Store store = storeMap.get(tokens[1]);
                    if(store == null){
                        System.out.println("ERROR:store_identifier_does_not_exist");
                        continue;
                    }
                    store.displayAllOrders();
                    System.out.println("OK:display_completed");
                    //System.out.println("store: " + tokens[1]);

                } else if (tokens[0].equals("request_item")) {

                    Store store = storeMap.get(tokens[1]);
                    if(store == null){
                        System.out.println("ERROR:store_identifier_does_not_exist");
                        continue;
                    }
                    Order order = store.getOrder(tokens[2]);
                    if(order == null){
                        System.out.println("ERROR:order_identifier_does_not_exist");
                        continue;
                    }
                    Item item = storeMap.get(tokens[1]).getItem(tokens[3]);
                    if(item == null){
                        System.out.println("ERROR:item_identifier_does_not_exist");
                        continue;
                    }
                    if(order.containsItem(item.getItemName())){
                        System.out.println("ERROR:item_already_ordered");
                        continue;
                    }
                    int unitWeight = item.getWeight();
                    Drone drone = store.getDrone(order.getDroneId());
                    Customer customer = customerMap.get(order.getCustomer());
                    Entry entry = new Entry(tokens[1], tokens[3], Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), unitWeight);


                    if(customer.getCredit() - customer.getPendingCredit() < entry.getTotalCost()){
                        System.out.println("ERROR:customer_cant_afford_new_item");
                        continue;
                    }
                    if(drone.getRemainingCapacity() < entry.getTotalWeight()){
                        System.out.println("ERROR:drone_cant_carry_new_item");
                        continue;
                    }
                    order.requestItem(entry);
                    customer.setPendingCredit(customer.getPendingCredit() + entry.getTotalCost());
                    //drone.setTripsLeft(drone.getTripsLeft() - 1);
                    drone.setRemainingCapacity(drone.getRemainingCapacity() - entry.getTotalWeight());
                    System.out.println("OK:change_completed");
                    //kroger,purchaseA,pot_roast,3,9
                    //System.out.println("store: " + tokens[1] + ", order: " + tokens[2] + ", item: " + tokens[3] + ", quantity: " + tokens[4] + ", unit_price: " + tokens[5]);

                } else if (tokens[0].equals("purchase_order")) {
                    Store store = storeMap.get(tokens[1]);
                    if(store == null){
                        System.out.println("ERROR:store_identifier_does_not_exist");
                        continue;
                    }
                    Order order = store.getOrder(tokens[2]);
                    if(order == null){
                        System.out.println("ERROR:order_identifier_does_not_exist");
                        continue;
                    }
                    Drone drone = store.getDrone(order.getDroneId());
                    if(drone.getPilotID() == null){
                        System.out.println("ERROR:drone_needs_pilot");
                        continue;
                    }
                    if(drone.getTripsLeft() == 0){
                        System.out.println("ERROR:drone_needs_fuel");
                        continue;
                    }
                    String pilotId = store.processPurchase(tokens[2]);
                    if(pilotId != null){
                        Pilot pilot = pilotMap.get(pilotId);
                        pilot.gainExperience();
                    }
                    Customer customer = customerMap.get(order.getCustomer());
                    customer.setPendingCredit(customer.getPendingCredit() - order.getTotalCost());
                    customer.reduceCredit(order.getTotalCost());
                    System.out.println("OK:change_completed");
                    //System.out.println("store: " + tokens[1] + ", order: " + tokens[2]);

                } else if (tokens[0].equals("cancel_order")) {
                    Store store = storeMap.get(tokens[1]);
                    if(store == null){
                        System.out.println("ERROR:store_identifier_does_not_exist");
                        continue;
                    }
                    Order orderToRemove = store.getOrder(tokens[2]);
                    if(orderToRemove == null){
                        System.out.println("ERROR:order_identifier_does_not_exist");
                        continue;
                    }
                    Order removed = store.cancelOrder(tokens[2]);

                    int refund = removed.getTotalCost();
                    Customer customer = customerMap.get(removed.getCustomer());
                    customer.setPendingCredit(customer.getPendingCredit() + refund);

                    System.out.println("OK:change_completed");
                    //System.out.println("store: " + tokens[1] + ", order: " + tokens[2]);

                } else if (tokens[0].equals("stop")) {
                    System.out.println("stop acknowledged");
                    break;

                } else {
                    System.out.println("command " + tokens[0] + " NOT acknowledged");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
            }
        }

        System.out.println("simulation terminated");
        commandLineInput.close();
    }
}
