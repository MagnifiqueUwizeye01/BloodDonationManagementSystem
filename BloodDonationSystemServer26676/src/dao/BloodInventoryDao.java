package dao;

import model.BloodInventory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class BloodInventoryDao {

    public List<BloodInventory> getInventory() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT new model.BloodInventory(d.donor.bloodType, " +
             "SUM(d.quantity), " +
             "(SELECT COALESCE(SUM(r.quantity), 0) FROM BloodRequest r WHERE r.bloodType = d.donor.bloodType AND r.status = 'fulfilled'), " +
             "(SUM(d.quantity) - (SELECT COALESCE(SUM(r.quantity), 0) FROM BloodRequest r WHERE r.bloodType = d.donor.bloodType AND r.status = 'fulfilled'))" +
             ") FROM Donation d GROUP BY d.donor.bloodType";

            Query<BloodInventory> query = session.createQuery(hql, BloodInventory.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("❌ Error fetching inventory using Hibernate", e);
        }
    }
}
