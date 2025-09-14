package service;

import dao.DonationDao;
import model.Donation;
import rmi.DonationService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DonationServiceImpl extends UnicastRemoteObject implements DonationService {

    private final DonationDao donationDao;

    public DonationServiceImpl() throws RemoteException {
        super();
        this.donationDao = new DonationDao();
    }

    @Override
    public int saveDonation(Donation donation) throws RemoteException {
        try {
            return donationDao.saveDonation(donation);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Failed to save donation", e);
        }
    }

    @Override
    public boolean deleteDonation(int donationId) throws RemoteException {
        try {
            return donationDao.deleteDonation(donationId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Failed to delete donation", e);
        }
    }

    @Override
    public boolean updateDonation(Donation donation) throws RemoteException {
        try {
            return donationDao.updateDonation(donation);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Failed to update donation", e);
        }
    }

    @Override
    public List<Donation> getAllDonations() throws RemoteException {
        try {
            return donationDao.getAllDonations();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Failed to get all donations", e);
        }
    }

    @Override
    public List<Donation> getDonationsByDonor(int donorId) throws RemoteException {
        try {
            return donationDao.getDonationsByDonor(donorId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Failed to get donations by donor", e);
        }
    }
}
