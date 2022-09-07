package VendingMachine.service;

/**
 * @author Ryan Taylor
 * @created 03/07/2022 - 02:38
 * Class which handles the exception when the vending machine does not have
 * any stock for an item.
 */
public class VendingMachineStockException extends Exception {
    public VendingMachineStockException (String message) {
        super(message);
    }

    public VendingMachineStockException (String message, Throwable cause) {
        super(message, cause);
    }
}
