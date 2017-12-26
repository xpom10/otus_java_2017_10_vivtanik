package ru.otus.DAO;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.otus.UserData.UserDataSet;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final Session session;

    public UserDAOImpl(Session session) {
        this.session = session;
    }

    @Override
    public void save(UserDataSet user) {
        session.save(user);
    }

    @Override
    public UserDataSet load(long id) {
        return session.load(UserDataSet.class, id);
    }

    @Override
    public List<UserDataSet> getAllUsers() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserDataSet> criteriaQuery = builder.createQuery(UserDataSet.class);
        criteriaQuery.from(UserDataSet.class);
        return session.createQuery(criteriaQuery).list();
    }

    @Override
    public UserDataSet getByName(String name) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserDataSet> criteria = builder.createQuery(UserDataSet.class);
        Root<UserDataSet> from = criteria.from(UserDataSet.class);
        criteria.where(builder.equal(from.get("name"), name));
        Query<UserDataSet> query = session.createQuery(criteria);
        return  query.uniqueResult();
    }
}
