package org.belhard.hibernate.dao.impl;

import org.belhard.hibernate.dao.EmployeeRepository;
import org.belhard.hibernate.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final EntityManager entityManager;
    public static final String FIND_ALL = "from Employee";
    public static final String DELETE = "delete from Employee where id = :id";

    public EmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Employee find(Long key) {
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, key);
        entityManager.getTransaction().commit();
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        entityManager.getTransaction().begin();
        List<Employee> employees = entityManager.createQuery(FIND_ALL, Employee.class).getResultList();
        entityManager.getTransaction().commit();
        return employees;
    }

    @Override
    public void save(Employee entity) {
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
