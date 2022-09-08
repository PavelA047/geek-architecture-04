package com.example.demo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> findAll() {
        return entityManager.createQuery("select p from Product p", Product.class)
                .getResultList();
    }

    public Product getProductByID(long id) {
        return entityManager.find(Product.class, id);
    }

    @Transactional
    public void save(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
        } else entityManager.merge(product);
    }

    @Transactional
    public void delete(long id) {
        entityManager.remove(entityManager.find(Product.class, id));
    }
}
