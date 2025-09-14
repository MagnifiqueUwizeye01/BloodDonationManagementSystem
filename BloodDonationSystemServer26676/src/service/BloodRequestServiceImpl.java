package service;

import dao.BloodRequestDao;
import model.BloodRequest;
import rmi.BloodRequestService;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.List;

public class BloodRequestServiceImpl extends UnicastRemoteObject implements BloodRequestService {
    private final BloodRequestDao requestDao = new BloodRequestDao();

    public BloodRequestServiceImpl() throws RemoteException {}

    @Override
    public int saveRequest(BloodRequest request) throws RemoteException {
        try {
            return requestDao.saveRequest(request);
        } catch (Exception e) {
            throw new RemoteException("Failed to save blood request", e);
        }
    }

    @Override
    public boolean updateRequest(BloodRequest request) throws RemoteException {
        try {
            return requestDao.updateRequest(request);
        } catch (Exception e) {
            throw new RemoteException("Failed to update blood request", e);
        }
    }

    @Override
    public boolean deleteRequest(int requestId) throws RemoteException {
        try {
            return requestDao.deleteRequest(requestId);
        } catch (Exception e) {
            throw new RemoteException("Failed to delete blood request", e);
        }
    }

    @Override
    public List<BloodRequest> getAllRequests() throws RemoteException {
        try {
            return requestDao.getAllRequests();
        } catch (Exception e) {
            throw new RemoteException("Failed to retrieve blood requests", e);
        }
    }
}
