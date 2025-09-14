package dao;

import model.Donation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class DonationDao {

    public int saveDonation(Donation donation) throws Exception {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            int id = (int) session.save(donation);
            tx.commit();
            return id;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw new Exception("❌ Failed to save donation: " + e.getMessage());
        }
    }

    public List<Donation> getAllDonations() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Donation ORDER BY donationDate DESC";
            Query<Donation> query = session.createQuery(hql, Donation.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("❌ Failed to retrieve donations: " + e.getMessage());
        }
    }

    public List<Donation> getDonationsByDonor(int donorId) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Donation WHERE donor.donorId = :donorId ORDER BY donationDate DESC";
            Query<Donation> query = session.createQuery(hql, Donation.class);
            query.setParameter("donorId", donorId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("❌ Failed to retrieve donations by donor: " + e.getMessage());
        }
    }

    public boolean deleteDonation(int donationId) throws Exception {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Donation donation = session.get(Donation.class, donationId);
            if (donation == null) return false;

            tx = session.beginTransaction();
            session.delete(donation);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw new Exception("❌ Failed to delete donation: " + e.getMessage());
        }
    }

    public boolean donorHasDonations(int donorId) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT count(*) FROM Donation WHERE donor.donorId = :donorId";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("donorId", donorId);
            return query.uniqueResult() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("❌ Failed to check donor donations: " + e.getMessage());
        }
    }

    public boolean updateDonation(Donation donation) throws Exception {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(donation);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw new Exception("❌ Failed to update donation: " + e.getMessage());
        }
    }
}
