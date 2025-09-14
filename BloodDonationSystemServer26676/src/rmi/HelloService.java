package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

// This is the remote interface the client will call
public interface HelloService extends Remote {
    String sayHello(String name) throws RemoteException;
}
