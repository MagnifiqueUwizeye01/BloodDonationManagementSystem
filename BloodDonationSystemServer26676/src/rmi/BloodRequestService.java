package rmi;

import model.BloodRequest;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BloodRequestService extends Remote {
    int saveRequest(BloodRequest request) throws RemoteException;
    boolean updateRequest(BloodRequest request) throws RemoteException;
    boolean deleteRequest(int requestId) throws RemoteException;
    List<BloodRequest> getAllRequests() throws RemoteException;
}
