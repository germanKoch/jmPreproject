package web.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Role> getAllRoles() {
        return sessionFactory.getCurrentSession().createQuery("from Role").list();
    }

    @Override
    public Role getRoleByName(String name) {
        Query query =  sessionFactory.getCurrentSession().createQuery("from Role where name = :name");
        query.setParameter("name",name);
        return (Role) query.list().get(0);
    }
}
