package dao;

import model.BloodRequest;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class BloodRequestDao {

    public int saveRequest(BloodRequest request) throws Exception {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            int generatedId = (int) session.save(request);
            tx.commit();
            return generatedId;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw new Exception("❌ Failed to save blood request: " + e.getMessage());
        }
    }

    public boolean updateRequest(BloodRequest request) throws Exception {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(request);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw new Exception("❌ Failed to update blood request: " + e.getMessage());
        }
    }

    public List<BloodRequest> getAllRequests() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM BloodRequest ORDER BY requestDate DESC";
            Query<BloodRequest> query = session.createQuery(hql, BloodRequest.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("❌ Failed to retrieve blood requests: " + e.getMessage());
        }
    }

    public BloodRequest getRequestById(int requestId) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(BloodRequest.class, requestId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("❌ Failed to get request by ID: " + e.getMessage());
        }
    }

    public boolean deleteRequest(int requestId) throws Exception {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            BloodRequest request = session.get(BloodRequest.class, requestId);
            if (request == null) return false;

            tx = session.beginTransaction();
            session.delete(request);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw new Exception("❌ Failed to delete blood request: " + e.getMessage());
        }
    }

    public List<BloodRequest> getRequestsByStatus(String status) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM BloodRequest WHERE status = :status ORDER BY requestDate DESC";
            Query<BloodRequest> query = session.createQuery(hql, BloodRequest.class);
            query.setParameter("status", status);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("❌ Failed to filter requests by status: " + e.getMessage());
        }
    }

    public List<BloodRequest> getRequestsByBloodType(String bloodType) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM BloodRequest WHERE bloodType = :bloodType ORDER BY requestDate DESC";
            Query<BloodRequest> query = session.createQuery(hql, BloodRequest.class);
            query.setParameter("bloodType", bloodType);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("❌ Failed to filter requests by blood type: " + e.getMessage());
        }
    }
}
