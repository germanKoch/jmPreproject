package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User userByCarSeries(int series) {
       TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car where series = :series");
       query.setParameter("series",series);
       List<Car> list = query.getResultList();
       if (!list.isEmpty()) {
          return list.get(0).getUser();
       }

       return null;
    }
}
