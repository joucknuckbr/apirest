package com.HP.apirest.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public Personage saveOne(Personage personage){
        
        entityManager.persist(personage);
        
        return entityManager.find(Personage.class, personage.getId());
        
    }
    
    @Transactional
    public List<Personage> findAll() {
        
        return entityManager.createQuery("FROM Personage").getResultList();
        
    }
    
    @Transactional
    public Personage findOne(Long id) {
        
        return entityManager.find(Personage.class, id);
        
    }
    
    @Transactional
    public Personage deleteById(Long id) {
        
        Personage personage = entityManager.find(Personage.class, id);
        
        if (personage == null) {
            
            return null;
            
        }
        
        entityManager.remove(personage);
        
        return personage;
    }
    
    @Transactional
    public Personage updateById(Long id, Personage personage) throws ParseException {
        
        Personage merge = entityManager.find(Personage.class, id);
        
        if(merge == null){
            return null;
        }
        
        merge.setName(personage.getName());
        merge.setAlternate_names(personage.getAlternate_names());
        merge.setSpecies(personage.getSpecies());
        merge.setGender(personage.getGender());
        merge.setHouse(personage.getHouse());
        
        Date format = new SimpleDateFormat("dd-MM-yyyy").parse(personage.getDateOfBirth());
        format.setDate(format.getDate()+1);
        merge.setDateOfBirth(format);
        
        merge.setYearOfBirth(personage.getYearOfBirth());
        merge.setWizard(personage.isWizard());
        merge.setAncestry(personage.getAncestry());
        merge.setEyeColour(personage.getEyeColour());
        merge.setHairColour(personage.getHairColour());
        merge.setWand(personage.getWand());
        merge.setPatronus(personage.getPatronus());
        merge.setHogwartsStudent(personage.isHogwartsStudent());
        merge.setHogwartsStaff(personage.isHogwartsStudent());
        merge.setActor(personage.getActor());
        merge.setAlternate_actors(personage.getAlternate_actors());        
        merge.setAlive(personage.isAlive());
        merge.setImage(personage.getImage());
        
        entityManager.merge(merge);
        
        return merge;
        
    }
    
    @Transactional
    public Integer verify() {
        
        return entityManager.createQuery("FROM Personage WHERE ROWNUM <= 1").getResultList().size();
        
    }
    
}