package com.example.librarymanagement.repository;

import com.example.librarymanagement.entity.Publisher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class PublisherDaoImpl implements PublisherDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Publisher save(Publisher entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Publisher update(Publisher entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        Publisher entity = findById(id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public Publisher findById(Long id) {
        return entityManager.find(Publisher.class, id);
    }

    @Override
    public List<Publisher> findAll() {
        return entityManager.createQuery("FROM Publisher", Publisher.class).getResultList();
    }
}
