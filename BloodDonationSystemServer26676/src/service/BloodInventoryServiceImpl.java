package service;

import dao.BloodInventoryDao;
import model.BloodInventory;
import rmi.BloodInventoryService;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.List;

public class BloodInventoryServiceImpl extends UnicastRemoteObject implements BloodInventoryService {

    private final BloodInventoryDao inventoryDao;

    public BloodInventoryServiceImpl() throws RemoteException {
        super();
        this.inventoryDao = new BloodInventoryDao(); // ✅ Hibernate DAO now, no JDBC
    }

    @Override
    public List<BloodInventory> getInventory() throws RemoteException {
        try {
            return inventoryDao.getInventory(); // This now uses Hibernate inside DAO
        } catch (Exception e) {
            throw new RemoteException("❌ Failed to retrieve inventory", e);
        }
    }
}
