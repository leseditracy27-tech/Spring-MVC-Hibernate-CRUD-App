package main.java.web.dao;
import main.java.web.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    // 1️⃣ Get all users
    @Override
    public List<User> findAll() {
        return entityManager
                .createQuery("from User", User.class)
                .getResultList();
    }


    // 2️⃣ Save or Update user
    @Override
    public void save(User user) {
        entityManager.merge(user);
    }

    // 3️⃣ Find user by ID
    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    // 4️⃣ Delete user
    @Override
    public void delete(Long id) {
        User user = findById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}