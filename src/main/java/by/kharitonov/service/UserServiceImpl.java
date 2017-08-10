package by.kharitonov.service;

import by.kharitonov.dao.RoleDao;
import by.kharitonov.dao.UserDao;
import by.kharitonov.model.Role;
import by.kharitonov.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    @Qualifier("roleDao")
    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return this.userDao.findByUsername(username);
    }

    @Override
    @Transactional
    public void addUser(User user) {
//        this.userDao.addUser(user);
        List<Role> roles = new ArrayList<>();
        roles.add(roleDao.getById(2));
        user.setRoles(roles);
        this.userDao.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        this.userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        this.userDao.removeUser(id);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return this.userDao.getUserById(id);
    }

    @Override
    @Transactional
    public List<User> listUser() {
        return this.userDao.listUser();
    }



}
