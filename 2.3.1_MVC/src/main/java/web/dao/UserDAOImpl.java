package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void deleteUser(long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("delete User where id = :id");
        query.setParameter("id",id);
        query.executeUpdate();
    }

    @Override
    public void changeUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }
}
