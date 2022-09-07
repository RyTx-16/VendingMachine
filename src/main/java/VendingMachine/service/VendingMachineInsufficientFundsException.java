package VendingMachine.service;

/**
 * @author Ryan Taylor
 * @created 05/07/2022 - 02:42
 * Class which handles the exception when the vending machine does not have
 * the given amount of money for an item.
 */
public class VendingMachineInsufficientFundsException extends Exception {
    public VendingMachineInsufficientFundsException (String message) {
        super(message);
    }

    public VendingMachineInsufficientFundsException (String message, Throwable cause) {
        super(message, cause);
    }
}
