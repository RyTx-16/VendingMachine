package VendingMachine.ui;

import VendingMachine.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ryan Taylor
 * @created 02/07/2022 - 02:03
 * Handles the UI logic.Contains predominately print statement methods
 * using the UserIO class for any user input. CANNOT ACCESS DAO.
 */

@Component
public class VendingMachineView {
    private UserIO io;

    @Autowired
    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndSelect() {
        io.print("--------------------------------------");
        io.print("1.\tInsert Money");
        io.print("2.\tExit");
        io.print("--------------------------------------");
        return io.readInt("Please select an option: ",1 ,2);
    }

    public BigDecimal printMoney(){
        io.print("--------------------------------------");
        return io.readBigDecimal("Please input some money: ");
    }

    public void printGoodbye() {
        io.print("--------------------------------------");
        io.print("Thanks for using the vending machine, Goodbye!");
        io.print("--------------------------------------");
    }

    public void printInventory(List<Item> itemList) {
        for (Item currentItem : itemList) {
            String itemInfo = String.format("%s.  %s, £%s, Stock: %s",
                    currentItem.getItemId(),
                        currentItem.getName(),
                            currentItem.getCost(),
                                currentItem.getInInventory());
            io.print(itemInfo);
        }
    }

    public int getItemChoice() {
        io.print("--------------------------------------");
        return io.readInt("Please select an item id: ",1 ,8);
    }

    public void removeItem() {
        io.print("--------------------------------------");
        io.readString("Enjoy your purchase! Press any key to continue.");
    }

    public void printInventoryBanner() {
        io.print("--------------------------------------");
        io.print("\t\tVending Machine Stock!");
        io.print("--------------------------------------");
    }

    public void printInvalid() {
        io.print("--------------------------------------");
        io.print("Invalid option.");
    }

    public void printNoStock() {
        io.print("--------------------------------------");
        io.print("Sorry, no stock for this item.");
    }

    public void printNoMoney() {
        io.print("--------------------------------------");
        io.print("Not enough money inputted.");
    }
}

