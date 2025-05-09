package com.example.librarymanagement.repository;

import com.example.librarymanagement.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book save(Book entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Book update(Book entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        Book entity = findById(id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public Book findById(Long id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public List<Book> findAll() {
        return entityManager.createQuery("FROM Book", Book.class).getResultList();
    }
}
