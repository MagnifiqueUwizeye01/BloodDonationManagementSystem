package service;

import rmi.DonorService;
import model.Donor;
import dao.DonorDao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * ✅ Remote service for managing donors.
 * All persistence logic is delegated to DonorDao, which uses Hibernate ORM.
 */
public class DonorServiceImpl extends UnicastRemoteObject implements DonorService {
   

    public DonorServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String testDonorConnection(Donor donor) throws RemoteException {
        return "Received donor: " + donor.getNames() + " [Blood: " + donor.getBloodType() + "]";
    }

    @Override
    public int saveDonor(Donor donor) throws RemoteException {
        try {
            // 💡 Hibernate used in DonorDao.saveDonor()
            return new DonorDao().saveDonor(donor);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("❌ Failed to save donor: " + e.getMessage());
        }
    }

    @Override
    public List<Donor> getAllDonors() throws RemoteException {
        try {
            return new DonorDao().getAllDonors(); // ✅ Uses Hibernate
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("❌ Failed to fetch donors", e);
        }
    }

    @Override
    public boolean donorExists(String name, String phone) throws RemoteException {
        try {
            return new DonorDao().donorExists(name, phone);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("❌ Error checking donor existence", e);
        }
    }

    @Override
    public boolean updateDonor(Donor donor) throws RemoteException {
        try {
            return new DonorDao().updateDonor(donor);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("❌ Error updating donor", e);
        }
    }

    @Override
    public boolean deleteDonor(int donorId) throws RemoteException {
        try {
            // 🛠 Fixed: now returns boolean
            return new DonorDao().deleteDonor(donorId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("❌ Error deleting donor", e);
        }
    }

    @Override
    public boolean hasDonations(int donorId) throws RemoteException {
        try {
            return new DonorDao().hasDonations(donorId);
        } catch (Exception e) {
            throw new RemoteException("❌ Error checking donor donations", e);
        }
    }

    @Override
    public boolean donorExists(int donorId) throws RemoteException {
        try {
            return new DonorDao().donorExists(donorId);
        } catch (Exception e) {
            throw new RemoteException("❌ Error checking donor by ID", e);
        }
    }
    
    @Override
public List<String> getAllDonorsFormatted() throws RemoteException {
    try {
        return new DonorDao().getAllDonorsFormatted();
    } catch (Exception e) {
        e.printStackTrace();
        throw new RemoteException("Error fetching formatted donor list", e);
    }
}

}
