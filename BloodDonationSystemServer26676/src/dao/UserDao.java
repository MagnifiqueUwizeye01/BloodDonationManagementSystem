package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

public class UserDao {

    public boolean registerUser(User user) throws Exception {
        validateUser(user);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            if (userExists(user.getUsername(), user.getEmail(), session)) {
                return false;
            }

            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed to register user: " + e.getMessage());
        }
    }

    private void validateUser(User user) throws Exception {
        if (user.getNationalId() == null || user.getNationalId().trim().isEmpty()) {
            throw new Exception("National ID is required");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new Exception("Username is required");
        }
        if (user.getEmail() == null || !user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new Exception("Invalid email format");
        }
        if (user.getPassword() == null || user.getPassword().length() < 8) {
            throw new Exception("Password must be at least 8 characters");
        }
        if (user.getPhoneNumber() == null || !user.getPhoneNumber().matches("^\\+?[0-9]{10,15}$")) {
            throw new Exception("Phone must be 10-15 digits (optional + prefix)");
        }
    }

    private boolean userExists(String username, String email, Session session) {
        String hql = "FROM User WHERE username = :username OR email = :email";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("username", username);
        query.setParameter("email", email);

        return !query.list().isEmpty();
    }

    public User getUser(String username, String password) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM User WHERE username = :username AND password = :password";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);

            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Login failed: " + e.getMessage());
        }
    }
}
