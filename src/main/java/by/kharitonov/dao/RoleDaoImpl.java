package by.kharitonov.dao;

import by.kharitonov.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role getById(int id) {

        Session session = this.sessionFactory.getCurrentSession();
        Role role = (Role) session.load(Role.class, new Integer(id));
        return role;
    }
}
