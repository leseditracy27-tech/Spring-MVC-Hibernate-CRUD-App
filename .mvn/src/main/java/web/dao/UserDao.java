package main.java.web.dao;

import main.java.web.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    void save(User user);

    User findById(Long id);

    void delete(Long id);
}