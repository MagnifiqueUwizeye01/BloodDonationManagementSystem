package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Donor;

public interface DonorService extends Remote {
    String testDonorConnection(Donor donor) throws RemoteException;

    int saveDonor(Donor donor) throws RemoteException;

    List<Donor> getAllDonors() throws RemoteException;

    boolean donorExists(String name, String phone) throws RemoteException;

    boolean donorExists(int donorId) throws RemoteException; // ✅ New for search button in DonationForm

    boolean updateDonor(Donor donor) throws RemoteException;

    boolean deleteDonor(int donorId) throws RemoteException;

    boolean hasDonations(int donorId) throws RemoteException; // used in delete logic
    
    List<String> getAllDonorsFormatted() throws RemoteException;
}
