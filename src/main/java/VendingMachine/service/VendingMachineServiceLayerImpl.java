package VendingMachine.service;

import VendingMachine.dao.VendingMachineAuditDao;
import VendingMachine.dao.VendingMachineDao;
import VendingMachine.dao.VendingMachinePersistenceException;
import VendingMachine.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author Ryan Taylor
 * @created 02/07/2022 - 01:42
 */

@Component
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    VendingMachineDao dao;
    private final VendingMachineAuditDao auditDao;

    @Autowired
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void checkMoney() throws VendingMachineInsufficientFundsException, VendingMachinePersistenceException {
        auditDao.writeAuditEntry("Transaction cancelled due to insufficient funds.");
            throw new VendingMachineInsufficientFundsException("Error: Insufficient funds.");
    }

    @Override
    public List<Item> getItems() throws VendingMachinePersistenceException {
        return dao.getItems();
    }

    @Override
    public int removeItemInv(String itemId) throws VendingMachinePersistenceException {
        int removeItemInv = dao.updateItem(itemId);
        auditDao.writeAuditEntry("Item: " + itemId + ", inventory reduced by 1.");
        return removeItemInv;
    }
}

