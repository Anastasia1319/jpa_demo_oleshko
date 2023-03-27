package org.belhard.hibernate.dao.impl;

import org.belhard.hibernate.dao.DepartmentRepository;
import org.belhard.hibernate.entity.Department;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class DepartmentRepositoryImpl implements DepartmentRepository {

    private final EntityManager entityManager;
    public static final String FIND_ALL = "from Department";
    public static final String DELETE = "delete from Department where id = :id";

    public DepartmentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Department find(Long key) {
        entityManager.getTransaction().begin();
        Department department = entityManager.find(Department.class, key);
        entityManager.getTransaction().commit();
        return department;
    }

    @Override
    public List<Department> findAll() {
        entityManager.getTransaction().begin();
        List<Department> departments = entityManager.createQuery(FIND_ALL, Department.class).getResultList();
        entityManager.getTransaction().commit();
        return departments;
    }

    @Override
    public void save(Department entity) {
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
