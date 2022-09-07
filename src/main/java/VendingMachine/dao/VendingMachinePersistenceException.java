package VendingMachine.dao;

/**
 * @author Ryan Taylor
 * @created 02/07/2022 - 02:21
 * Error class for the application.
 */
public class VendingMachinePersistenceException extends Exception {
    public VendingMachinePersistenceException(String message) {
        super(message);
    }

    public VendingMachinePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}

