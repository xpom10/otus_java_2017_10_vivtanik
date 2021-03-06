package ru.otus.DataBase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.otus.DAO.UserDAOImpl;
import ru.otus.UserData.UserDataSet;

import java.util.List;
import java.util.function.Function;

public class DBServiceHibernateImpl implements DBService {

    private final SessionFactory sessionFactory;

    public DBServiceHibernateImpl() {
        sessionFactory = ConnectionHelper.getSessionFactory();
    }


    @Override
    public String getLocalStatus() {
        return runInSession(session -> session.getTransaction().getStatus().name());
    }

    @Override
    public void save(UserDataSet user) {
        try(Session session = sessionFactory.openSession()) {
            UserDAOImpl dao = new UserDAOImpl(session);
            dao.save(user);
        }
    }

    @Override
    public UserDataSet load(long id) {
        return runInSession(session -> {
            UserDAOImpl dao = new UserDAOImpl(session);
            return dao.load(id);
        });
    }

    @Override
    public List<UserDataSet> getAllUsers() {
        return runInSession(session -> {
            UserDAOImpl dao = new UserDAOImpl(session);
            return dao.getAllUsers();
        });
    }


    @Override
    public UserDataSet getByName(String name) {
        return runInSession(session -> {
            UserDAOImpl dao = new UserDAOImpl(session);
            return dao.getByName(name);
        });
    }

    @Override
    public void shutdown() {
        sessionFactory.close();

    }

    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }
}
