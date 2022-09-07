package VendingMachine.dto;

import java.math.BigDecimal;

/**
 * @author Ryan Taylor
 * @created 02/07/2022 - 00:20
 * Data Transfer Object Class. Holds the data for a piece of food/drink which is then stored.
 */
public class Item {
    private String item_id;
    private String name;
    private BigDecimal cost;
    private int inInventory;

    public Item(String item_id) {
        this.item_id = item_id;
    }

    public Item(String x, BigDecimal bigDecimal, int i) {
        this.name = x;
        this.cost = bigDecimal;
        this.inInventory = i;
    }


    /*
    public Item(String name, String cost, int inInventory) {
        this.id = uniqueId++;
        this.name = name;
        this.cost = new BigDecimal(cost);
        this.inInventory = inInventory;
        //vendingMachineInventory.add(this);
    }

    public Item(Map<Integer, String> items) {
        this.id = uniqueId++;
        this.name = items.get(0);
        this.cost = new BigDecimal(items.get(1));
        this.inInventory = Integer.parseInt(items.get(2));
        //vendingMachineInventory.add(this);
    }
     */

    public String getItemId() {
        return item_id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getInInventory() {
        return inInventory;
    }

    public int setInInventory(int inInventory) {
        return this.inInventory = inInventory;
    }

    @Override
    public String toString() {
        return  getItemId() + ".\t" + name + ", Price: " + cost + ", Available: "+ inInventory;
    }
}



