package com.HP.apirest.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HP.apirest.model.Personage;
import com.HP.apirest.repository.PersonageRepository;

@Service
public class PersonageService {
    
    @Autowired
    PersonageRepository repository;

    public Personage addNewPersonage(Personage personage) {
    
        return repository.saveOne(personage);
    
    }

    public List<Personage> getAllPersonages() {
    
        return repository.findAll();
    
    }

    public Personage getPersonage(Long id) {

        return repository.findOne(id);

    }

    public Personage deletePersonage(Long id) {
    
        return repository.deleteById(id);
    
    }

    public Personage updatePersonage(Long id, Personage personage) throws ParseException {
        
        return repository.updateById(id, personage);
    
    }

    public boolean verifyDatabase() {

        if (repository.verify() > 0) {
            return true;
        } else {
            return false;
        }

    }
}
