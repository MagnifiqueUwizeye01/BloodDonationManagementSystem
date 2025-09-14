package dao;

import model.Donor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class DonorDao {

    public int saveDonor(Donor donor) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            int id = (int) session.save(donor);
            tx.commit();
            return id;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return -1;
        }
    }

    public boolean updateDonor(Donor donor) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(donor);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean donorExists(String name, String phone) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Donor WHERE lower(trim(names)) = :name AND replace(phone, ' ', '') = :phone";
            Query<Donor> query = session.createQuery(hql, Donor.class);
            query.setParameter("name", name.trim().toLowerCase());
            query.setParameter("phone", phone.replaceAll("\\s", ""));
            return !query.list().isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Donor> getAllDonors() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Donor ORDER BY names", Donor.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<String> getAllDonorsFormatted() {
        List<String> formatted = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Donor> donors = session.createQuery("FROM Donor ORDER BY names", Donor.class).list();
            for (Donor d : donors) {
                formatted.add(String.format("%s (ID: %d, Blood: %s)", d.getNames(), d.getDonorId(), d.getBloodType()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formatted;
    }

    public boolean deleteDonor(int donorId) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Donor donor = session.get(Donor.class, donorId);
            if (donor == null) return false;

            tx = session.beginTransaction();
            session.delete(donor);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean hasDonations(int donorId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Donor donor = session.get(Donor.class, donorId);
            return donor != null && donor.getDonations() != null && !donor.getDonations().isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Donor getDonorById(int donorId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Donor.class, donorId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Donor> getDonorsByBloodType(String bloodType) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Donor WHERE bloodType = :bloodType ORDER BY names";
            Query<Donor> query = session.createQuery(hql, Donor.class);
            query.setParameter("bloodType", bloodType);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean donorExists(int donorId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Donor.class, donorId) != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
