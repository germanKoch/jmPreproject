package jm.task.core.jdbc.dao;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import jm.task.core.jdbc.model.User;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    Session session;

    public UserDaoHibernateImpl() {

    }

    public UserDaoHibernateImpl(Session session) {
        this.session = session;
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS " +
                "Users(id bigint primary key, name varchar(45), lastName varchar(45), age tinyint)");
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DROP TABLE IF EXISTS Users");
        query.executeUpdate();
        transaction.commit();
        session.close();

    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE User WHERE id = (:id)");
        query.setParameter("id",id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = session.beginTransaction();
        List<User> list = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE User").executeUpdate();
        transaction.commit();
        session.close();
    }
}
