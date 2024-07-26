package web.dao;

//import net.bytebuddy.dynamic.DynamicType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao{

//    @Autowired
//    private SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        //Session-реализация
//        sessionFactory.getCurrentSession().save(user);

        entityManager.persist(user);
    }

    @Override
    public List<User> listUsers() {
        //Session-реализация
//        TypedQuery<User> listFromDB = sessionFactory.getCurrentSession().createQuery("FROM User");
//        return listFromDB.getResultList();
        return entityManager.createQuery("SELECT U FROM User U", User.class).getResultList();
    }

    @Override
//    @SuppressWarnings("unchecked")
    public Optional<User> read(Long id) {
        //Session-реализация
//        TypedQuery<User> readUserQuery = sessionFactory.getCurrentSession().createQuery("FROM User WHERE id = :userID");
//        readUserQuery.setParameter("userID", id);
//        return readUserQuery.getResultStream().findFirst();

        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public void update(User user) {
        //Session-реализация
//        sessionFactory.getCurrentSession().saveOrUpdate(user);

        entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
        //Session-реализация
//        sessionFactory.getCurrentSession().delete(user);

        User userForDelete = entityManager.find(User.class, user.getID());
        entityManager.remove(userForDelete);
    }
}
