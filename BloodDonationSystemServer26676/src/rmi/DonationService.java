package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Donation;

public interface DonationService extends Remote {
    int saveDonation(Donation donation) throws RemoteException;
    boolean deleteDonation(int donationId) throws RemoteException;
    boolean updateDonation(Donation donation) throws RemoteException;
    List<Donation> getAllDonations() throws RemoteException;
    List<Donation> getDonationsByDonor(int donorId) throws RemoteException;
}
