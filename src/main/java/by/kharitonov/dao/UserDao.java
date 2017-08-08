package by.kharitonov.dao;


import by.kharitonov.model.User;

import java.util.List;

public interface UserDao {

    User findByUsername(String username);

    void addUser(User user);

    void updateUser(User user);

    void removeUser(int id);

    User getUserById(int id);

    List<User> listUser();


}
