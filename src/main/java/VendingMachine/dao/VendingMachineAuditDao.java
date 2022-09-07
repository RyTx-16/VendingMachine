package VendingMachine.dao;

/**
 * @author Ryan Taylor
 * @created 03/07/2022 - 02:58
 */
public interface VendingMachineAuditDao {
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}
