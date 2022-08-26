package com.HP.apirest.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.HP.apirest.model.Personage;

@Repository
public class PersonageRepository  {
    
    @PersistenceContext
    EntityManager entityManager;
    
    @Transactional
    public void saveAll(List<Personage> personage){
        entityManager.persist(personage);
    }
    
    @Transactional
    public void saveOne(Personage personage){
        entityManager.persist(personage);
    }
    
    @Transactional
    public List<Personage> findAll() {
        
        return entityManager.createQuery("FROM Personage").getResultList();
        
    }

    @Transactional
    public Personage deleteById(Long id) {
        Personage personage = entityManager.find(Personage.class, id);
        entityManager.remove(personage);
        return personage;
    }
    
    @Transactional
    public Integer verify() {
        return entityManager.createQuery("FROM Personage WHERE ROWNUM <= 1").getResultList().size();
    }
    
}