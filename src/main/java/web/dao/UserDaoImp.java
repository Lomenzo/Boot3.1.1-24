package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> listFromDB = sessionFactory.getCurrentSession().createQuery("FROM User");
        return listFromDB.getResultList();
    }

    @Override
//    @SuppressWarnings("unchecked")
    public Optional<User> read(Long id) {
        TypedQuery<User> readUserQuery = sessionFactory.getCurrentSession().createQuery("FROM User WHERE id = :userID");
        readUserQuery.setParameter("userID", id);
        return readUserQuery.getResultStream().findFirst();
    }

    @Override
    public void update(Long id, User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }


}
