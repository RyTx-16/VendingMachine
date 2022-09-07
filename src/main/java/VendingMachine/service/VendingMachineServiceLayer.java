package VendingMachine.service;

import VendingMachine.dao.VendingMachinePersistenceException;
import VendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;


/**
 * @author Ryan Taylor
 * @created 02/07/2022 - 01:42
 * Service layer interface for the vending machine.
 */
public interface VendingMachineServiceLayer {

    void checkMoney() throws VendingMachineInsufficientFundsException, VendingMachinePersistenceException;

    List<Item> getItems() throws VendingMachinePersistenceException;

    int removeItemInv(String itemId) throws VendingMachinePersistenceException, VendingMachineStockException;

}
