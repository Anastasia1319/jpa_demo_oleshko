package org.belhard.hibernate.dao.impl;

import org.belhard.hibernate.dao.StudentRepository;
import org.belhard.hibernate.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {
    private final EntityManager entityManager;
    public static final String FIND_ALL = "from Student";
    public static final String DELETE = "delete from Student where id = :id";

    public StudentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Student find(Long key) {
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, key);
        entityManager.getTransaction().commit();
        return student;
    }

    @Override
    public List<Student> findAll() {
        entityManager.getTransaction().begin();
        List<Student> students = entityManager.createQuery(FIND_ALL, Student.class).getResultList();
        entityManager.getTransaction().commit();
        return students;
    }

    @Override
    public void save(Student entity) {
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
