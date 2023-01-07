package org.belhard.hibernate.dao.impl;

import org.belhard.hibernate.dao.SectionRepository;
import org.belhard.hibernate.entity.Section;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class SectionRepositoryImpl implements SectionRepository {
    private final EntityManager entityManager;
    public static final String FIND_ALL = "from Section";
    public static final String DELETE = "delete from Section where id = :id";

    public SectionRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Section find(Long key) {
        entityManager.getTransaction().begin();
        Section section = entityManager.find(Section.class, key);
        entityManager.getTransaction().commit();
        return section;
    }

    @Override
    public List<Section> findAll() {
        entityManager.getTransaction().begin();
        List<Section> sections = entityManager.createQuery(FIND_ALL, Section.class).getResultList();
        entityManager.getTransaction().commit();
        return sections;
    }

    @Override
    public void save(Section entity) {
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
