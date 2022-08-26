package com.HP.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HP.apirest.model.Personage;
import com.HP.apirest.repository.PersonageRepository;

@Service
public class PersonageService {
    @Autowired
    PersonageRepository repository;

    public void addAllPersonages(List<Personage> personage) { 
        repository.saveAll(personage);
    }

    public void addNewPersonage(Personage personage) {
        repository.saveOne(personage);
    }

    public List<Personage> getAllPersonages() {
        return repository.findAll();
    }

    public void deletePersonage(Long id) {
        repository.deleteById(id);
    }

    public boolean verifyDatabase() {

        if (repository.verify() > 0) {
            return true;
        } else {
            return false;
        }

    }
}
