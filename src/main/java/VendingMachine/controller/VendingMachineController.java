package VendingMachine.controller;

import VendingMachine.dao.VendingMachineDao;
import VendingMachine.dao.VendingMachinePersistenceException;
import VendingMachine.dto.Item;
import VendingMachine.service.VendingMachineInsufficientFundsException;
import VendingMachine.service.VendingMachineServiceLayer;
import VendingMachine.service.VendingMachineStockException;
import VendingMachine.ui.UserIO;
import VendingMachine.ui.UserIOConsoleImpl;
import VendingMachine.ui.VendingMachineView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @author Ryan Taylor
 * @created 02/07/2022 - 01:40
 * Controller Class acts as the 'brains' of the application.
 * Controls the menu system and all of its functionality.
 * TALKS WITH VIEW AND DAO.
 */

@Component
public class VendingMachineController {
    private UserIO io = new UserIOConsoleImpl();
    private VendingMachineView view;
    private VendingMachineDao dao;
    private VendingMachineServiceLayer service;

    @Autowired
    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean active = true;
        int menuInput;
        int itemChoice;
        try {
            getInventory();
            while (active) {
                menuInput = getMenuSelection();

                switch (menuInput) {
                    case 1 -> {
                        BigDecimal money = getMoney();
                        itemChoice = getItemChoice();

                        if (getItem(itemChoice) > 0) {
                            // compare money amounts
                            if (Objects.equals(getMoneyValidation(itemChoice), money)) {
                                removeInventory(itemChoice);
                            } else if ((money.compareTo(getMoneyValidation(itemChoice)) > 0)) {
                                ////// DO CHANGE
                                System.out.println("Too much ");
                                removeInventory(itemChoice);
                            }
                            // Checks if money is < than item cost
                            else if ((money.compareTo(getMoneyValidation(itemChoice)) < 0)) {
                                getNoMoney();
                            }
                        } else if (getItem(itemChoice) <= 0) {
                            getNoStock();
                        }
                        active = false;
                    }
                    case 2 -> active = false;
                    default -> getInvalid();
                }
            }
            getGoodbye();
        } catch (VendingMachinePersistenceException | VendingMachineStockException | VendingMachineInsufficientFundsException e) {
            e.printStackTrace();
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndSelect();
    }

    private BigDecimal getMoney() {
        return view.printMoney();
    }

    private void getGoodbye() {
        view.printGoodbye();
    }

    private void getNoStock() {
        view.printNoStock();
    }

    private void getNoMoney() throws VendingMachineInsufficientFundsException, VendingMachinePersistenceException {
        //view.printNoMoney();
        service.checkMoney();
    }

    private void getInventory() throws VendingMachinePersistenceException {
        view.printInventoryBanner();
        List<Item> itemList = service.getItems();
        view.printInventory(itemList);
    }

    private int getItemChoice() {
        return view.getItemChoice();
    }

    private int getItem(int i) throws VendingMachinePersistenceException {
        //int itemId = view.getItemChoice();
        List<Item> itemList = service.getItems();
        return itemList.get(i-1).getInInventory();
        //view.displayItem(item);
    }

    private BigDecimal getMoneyValidation(int i) throws VendingMachinePersistenceException {
        List<Item> itemList = service.getItems();
        return itemList.get(i-1).getCost();
    }

    private void removeInventory(int y) throws VendingMachinePersistenceException, VendingMachineStockException {
        service.removeItemInv(String.valueOf(y));
        view.removeItem();
    }

    private void getInvalid() {
        view.printInvalid();
    }
}
