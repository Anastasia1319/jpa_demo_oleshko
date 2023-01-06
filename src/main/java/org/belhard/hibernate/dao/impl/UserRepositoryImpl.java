package org.belhard.hibernate.dao.impl;

import org.belhard.hibernate.dao.UserRepository;
import org.belhard.hibernate.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;
    public static final String FIND_ALL = "from User";
    public static final String DELETE = "delete from User where id = :id";

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User find(Long key) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, key);
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public List<User> findAll() {
        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery(FIND_ALL, User.class).getResultList();
        entityManager.getTransaction().commit();
        return users;
    }

    @Override
    public void save(User entity) {
        entityManager.getTransaction().begin();
        if (entity.getId() != null) {
            entityManager.merge(entity);
        } else {
            entityManager.persist(entity);
        }
        entityManager.getTransaction().commit();
    }


    @Override
    public boolean delete(Long key) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery(DELETE);
        query.setParameter("id", key);
        int result = query.executeUpdate();
        entityManager.getTransaction().commit();
        return result == 1;
    }
}
