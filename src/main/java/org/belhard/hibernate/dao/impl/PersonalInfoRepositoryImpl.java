package org.belhard.hibernate.dao.impl;

import org.belhard.hibernate.dao.PersonalInfoRepository;
import org.belhard.hibernate.entity.PersonalInfo;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class PersonalInfoRepositoryImpl implements PersonalInfoRepository {
    private final EntityManager entityManager;
    private static final String FIND_ALL = "from PersonalInfo";
    private static final String DELETE = "from PersonalInfo where id = :id";

    public PersonalInfoRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public PersonalInfo find(Long key) {
        entityManager.getTransaction().begin();
        PersonalInfo personalInfo = entityManager.find(PersonalInfo.class, key);
        entityManager.getTransaction().commit();
        return personalInfo;
    }

    @Override
    public List<PersonalInfo> findAll() {
        entityManager.getTransaction().begin();
        List<PersonalInfo> personalInfoList = entityManager.createQuery(FIND_ALL, PersonalInfo.class).getResultList();
        entityManager.getTransaction().commit();
        return personalInfoList;
    }

    @Override
    public void save(PersonalInfo entity) {
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
