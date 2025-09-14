package util;

import model.BloodInventory;
import model.BloodRequest;
import model.Donation;
import model.Donor;
import model.User;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("resources/hibernate.cfg.xml"); 

            // 🔗 Register all annotated entity classes
            configuration.addAnnotatedClass(Donor.class);
            configuration.addAnnotatedClass(Donation.class);
            configuration.addAnnotatedClass(BloodRequest.class);
            configuration.addAnnotatedClass(BloodInventory.class);
            configuration.addAnnotatedClass(User.class);


            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError("❌ Initial SessionFactory creation failed: " + ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}