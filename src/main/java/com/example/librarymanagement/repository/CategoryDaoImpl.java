package com.example.librarymanagement.repository;

import com.example.librarymanagement.entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Category save(Category entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Category update(Category entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        Category entity = findById(id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public Category findById(Long id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public List<Category> findAll() {
        return entityManager.createQuery("FROM Category", Category.class).getResultList();
    }
}
