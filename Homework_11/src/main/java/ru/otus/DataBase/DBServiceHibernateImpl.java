package ru.otus.DataBase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.otus.Cache.CacheEngineImpl;
import ru.otus.Cache.MyElement;
import ru.otus.DAO.UserDAOImpl;
import ru.otus.UserData.DataSet;
import ru.otus.UserData.UserDataSet;

import java.util.List;
import java.util.function.Function;

public class DBServiceHibernateImpl implements DBService {

    private final SessionFactory sessionFactory;
    private CacheEngineImpl<Long,DataSet> cache;

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
            System.out.println("Write to cache with id: " + user.getId());
            cache.put(user.getId(),new MyElement<>(user));
        }
    }

    @Override
    public UserDataSet load(long id) {
        return runInSession(session -> {
            UserDataSet user = (UserDataSet) cache.get(id);
            if ( user == null) {
                System.out.println("Return from DB and write to Cache with id: " + id);
                UserDAOImpl dao = new UserDAOImpl(session);
                UserDataSet userDataSet = dao.load(id);
                cache.put(id, new MyElement<>(userDataSet));
                return userDataSet;
            } else {
                System.out.println("Return from cache with id: " + id);
                return user;
            }
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

    public void registerCache(CacheEngineImpl<Long,DataSet> cache) {
        this.cache = cache;
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
