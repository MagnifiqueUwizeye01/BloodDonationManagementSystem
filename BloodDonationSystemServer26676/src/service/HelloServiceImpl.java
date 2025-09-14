package service;

import rmi.HelloService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// This class implements the remote method defined in HelloService
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    public HelloServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return "Hello, " + name + "! Server is working.";
    }
}
