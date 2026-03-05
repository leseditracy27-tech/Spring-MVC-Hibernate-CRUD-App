package main.java.web.service;

import main.java.web.dao.UserDao;
import main.java.web.model.User;
import main.java.web.service.UserService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserServicelmpl implements UserService {

    private final UserDao userDao;

    public UserServicelmpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(User user) {
         userDao.save(user);
    }

    @Override
    public User findById(Long id) {
       return userDao.findById(id);
    }

    @Override
    public void delete(Long id) {
userDao.delete(id);
    }
}