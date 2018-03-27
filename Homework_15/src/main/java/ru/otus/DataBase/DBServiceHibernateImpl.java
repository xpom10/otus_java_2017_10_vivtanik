package ru.otus.DataBase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.Cache.CacheEngine;
import ru.otus.Cache.MyElement;
import ru.otus.DAO.UserDAOImpl;
import ru.otus.MessageSystem.Address;
import ru.otus.MessageSystem.MessageSystem;
import ru.otus.MessageSystem.MessageSystemContext;
import ru.otus.UserData.DataSet;
import ru.otus.UserData.UserDataSet;

import java.util.List;
import java.util.function.Function;

public class DBServiceHibernateImpl implements DBService {

    private final SessionFactory sessionFactory;
    private final Address address;
    private CacheEngine<Long,DataSet> cache;
    private MessageSystem messageSystem;

    @Autowired
    public DBServiceHibernateImpl(CacheEngine<Long,DataSet> cache, MessageSystemContext systemContext) {
        sessionFactory = ConnectionHelper.getSessionFactory();

        this.address = systemContext.getDbAddress();
        this.messageSystem = systemContext.getMessageSystem();
        systemContext.getMessageSystem().addAddressee(this);

        this.cache = cache;
    }

    @Override
    public String getLocalStatus() {
        return runInSession(session -> session.getTransaction().getStatus().name());
    }

    @Override
    public long save(UserDataSet user) {
        try(Session session = sessionFactory.openSession()) {
            UserDAOImpl dao = new UserDAOImpl(session);
            dao.save(user);

            if (cache != null) {
                System.out.println("Write to cache with id: " + user.getId());
                cache.put(user.getId(), new MyElement<>(user));
            }
            return user.getId();
        }
    }

    @Override
    public UserDataSet load(long id) {
        UserDataSet user;
        if (cache != null) {
            user = (UserDataSet) cache.get(id);
            if (user != null) {
                System.out.println("Return from cache with id: " + id);
                return user;
            } else {
                user = runInSession(session -> {
                    UserDAOImpl dao = new UserDAOImpl(session);
                    return dao.load(id);
                });
                System.out.println("Return from DB and write to cache id: " + id);
                cache.put(id,new MyElement<>(user));
                return user;
            }
        } else return runInSession(session -> {
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

    public CacheEngine getCache() {
        return cache;
    }

    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MessageSystem getMessageSystem() {
        return messageSystem;
    }
}
