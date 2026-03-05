package main.java.web.service;

import main.java.web.model.User;
import java.util.List;

public interface UserService {

    List<User> findAll();

    void save(User user);

    User findById(Long id);

    void delete(Long id);
}