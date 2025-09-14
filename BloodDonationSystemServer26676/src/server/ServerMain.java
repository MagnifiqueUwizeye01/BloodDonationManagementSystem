package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.DonorService;
import rmi.DonationService;
import rmi.BloodRequestService;
import rmi.BloodInventoryService;

import service.DonorServiceImpl;
import service.DonationServiceImpl;
import service.BloodRequestServiceImpl;
import service.BloodInventoryServiceImpl;

public class ServerMain {
    public static void main(String[] args) {
        try {
            
            Registry registry;
            try {
                registry = LocateRegistry.getRegistry(5000);
                registry.list();
                System.out.println("[SERVER] ℹ️ RMI Registry already running on port 5000.");
            } catch (Exception ex) {
                registry = LocateRegistry.createRegistry(5000);
                System.out.println("[SERVER] ✅ RMI Registry started on port 5000.");
            }

            // ✅ Bind DonorService
            DonorService donorService = new DonorServiceImpl();
            registry.rebind("donor", donorService);
            System.out.println("[SERVER] ✅ DonorService bound and ready.");

            // ✅ Bind DonationService
            DonationService donationService = new DonationServiceImpl();
            registry.rebind("donation", donationService);
            System.out.println("[SERVER] ✅ DonationService bound and ready.");

            // ✅ Bind BloodRequestService
            BloodRequestService bloodRequestService = new BloodRequestServiceImpl();
            registry.rebind("bloodRequest", bloodRequestService);
            System.out.println("[SERVER] ✅ BloodRequestService bound and ready.");

            // ✅ Bind BloodInventoryService
            BloodInventoryService inventoryService = new BloodInventoryServiceImpl();
            registry.rebind("bloodInventory", inventoryService);
            System.out.println("[SERVER] ✅ BloodInventoryService bound and ready.");

        } catch (Exception e) {
            System.err.println("[SERVER] ❌ Server exception:");
            e.printStackTrace();
        }
    }
}
