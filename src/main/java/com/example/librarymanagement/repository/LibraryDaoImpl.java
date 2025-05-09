package com.example.librarymanagement.repository;

import com.example.librarymanagement.entity.Library;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class LibraryDaoImpl implements LibraryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Library save(Library entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Library update(Library entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        Library entity = findById(id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public Library findById(Long id) {
        return entityManager.find(Library.class, id);
    }

    @Override
    public List<Library> findAll() {
        return entityManager.createQuery("FROM Library", Library.class).getResultList();
    }
}
