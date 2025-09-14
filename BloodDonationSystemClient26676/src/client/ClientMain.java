package client;

import rmi.DonorService;
import rmi.DonationService;
import rmi.BloodRequestService;
import rmi.BloodInventoryService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientMain {
    public static void main(String[] args) {
        try {
            // RMI registry connection test
            Registry registry = LocateRegistry.getRegistry("localhost", 5000);

            DonorService donorService = (DonorService) registry.lookup("donor");
            DonationService donationService = (DonationService) registry.lookup("donation");
            BloodRequestService bloodRequestService = (BloodRequestService) registry.lookup("bloodRequest");
            BloodInventoryService inventoryService = (BloodInventoryService) registry.lookup("bloodInventory");

            System.out.println("[CLIENT] ✅ All services connected successfully.");
          
            System.out.println("[CLIENT] ✅ Connected to DonorService.");
            System.out.println("[CLIENT] ✅ Connected to DonationService.");
            System.out.println("[CLIENT] ✅ Connected to BloodRequestService.");
            System.out.println("[CLIENT] ✅ Connected to BloodInventoryService.");

            
            javax.swing.SwingUtilities.invokeLater(() -> {
               
            });

        } catch (Exception e) {
            System.err.println("[CLIENT] ❌ Client failed to connect:");
            e.printStackTrace();
        }
    }
}
