package by.kharitonov.service;

import by.kharitonov.dao.RoleDao;
import by.kharitonov.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    @Qualifier("roleDaoImpl")
    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getById(int id) {
        return this.roleDao.getById(id);
    }
}
