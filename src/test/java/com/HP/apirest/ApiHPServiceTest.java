package com.HP.apirest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;

import com.HP.apirest.model.Personage;
import com.HP.apirest.service.PersonageService;

@EnableCaching 
@SpringBootTest
class ApiHPServiceTest {

	@Autowired
	private PersonageService service;

	@Test
	void shouldAddOnePersonage(){

		Personage personage = new Personage();

        personage.setName("HP");

		assertEquals(service.addNewPersonage(personage), personage);

	}

	@Test
	void verifyIfDatabaseHasData() throws Exception {

		Personage personage = new Personage();

        personage.setName("HP");
		service.addNewPersonage(personage);

		assertTrue(service.verifyDatabase());

	}

	@Test
	void shouldDeleteOnePersonageById() throws Exception {

		Personage personage = new Personage();
		long id = 1;

        personage.setName("HP");
		service.addNewPersonage(personage);

		long idDeleted = service.deletePersonage(id).getId();

		assertNull(service.getPersonage(idDeleted));

	}

	

}
