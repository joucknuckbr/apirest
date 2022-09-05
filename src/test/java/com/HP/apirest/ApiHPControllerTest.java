package com.HP.apirest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONArray;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.client.RestTemplate;

import com.HP.apirest.controller.PersonageController;
import com.HP.apirest.model.Personage;
import com.HP.apirest.model.Wand;

@EnableCaching
@WebMvcTest(PersonageController.class)
public class ApiHPControllerTest {

	@Test
	public void shouldSaveTheSameDataOfExternalApi() throws Exception {

		String uri = "http://hp-api.herokuapp.com/api/characters";
        RestTemplate restTemplateApiExternal = new RestTemplate();
        String result = restTemplateApiExternal.getForObject(uri, String.class);
		JSONArray jsonArr1 = new JSONArray(result);

		String myUri = "http://localhost:8080/pushAll";
        RestTemplate restTemplateMyApi = new RestTemplate();
		restTemplateMyApi.delete("http://localhost:8080/deleteAll");
        String myResult = restTemplateMyApi.getForObject(myUri, String.class);
		JSONArray jsonArr2 = new JSONArray(myResult);
		assertEquals(jsonArr1.length(), jsonArr2.length());
		
	}

	@Test
	public void shouldReturnCreatedPersonage() throws Exception {

		Personage personage = new Personage();
        personage.setName("Jane");

		Wand wand = new Wand();
		wand.setCore("Dragon Heart");
		wand.setWood("Accacia");
		wand.setLength(22.35);

		personage.setWand(wand);
		
		String uri = "http://localhost:8080/addNewPersonage";

		RestTemplate restTemplateMyApi = new RestTemplate();
        Personage myResult = restTemplateMyApi.postForObject(uri, personage, Personage.class);

		assertEquals(personage.getName(), myResult.getName());
	}

}
