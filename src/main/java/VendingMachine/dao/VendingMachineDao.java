package VendingMachine.dao;

import VendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author Ryan Taylor
 * @created 02/07/2022 - 01:41
 * Interface to store the DTO. In this class we create one method for each use case.
 */
public interface VendingMachineDao {

    List<Item> getItems()throws VendingMachinePersistenceException;

    int updateItem(String itemId) throws VendingMachinePersistenceException;


}
