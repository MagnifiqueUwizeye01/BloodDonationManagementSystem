package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.BloodInventory;

public interface BloodInventoryService extends Remote {
    List<BloodInventory> getInventory() throws RemoteException;
}
