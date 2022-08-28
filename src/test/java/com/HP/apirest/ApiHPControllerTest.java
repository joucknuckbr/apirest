package com.HP.apirest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONArray;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;

import com.HP.apirest.controller.PersonageController;
import com.HP.apirest.model.Personage;

@EnableCaching
@WebMvcTest(PersonageController.class)
public class ApiHPControllerTest {

	@Test
	@Order(1)
	public void shouldSaveTheSameDataOfExternalApi() throws Exception {

		String uri = "http://hp-api.herokuapp.com/api/characters";
        RestTemplate restTemplateApiExternal = new RestTemplate();
        String result = restTemplateApiExternal.getForObject(uri, String.class);
		JSONArray jsonArr1 = new JSONArray(result);

		String myUri = "http://localhost:8080/pushAll";
        RestTemplate restTemplateMyApi = new RestTemplate();
        String myResult = restTemplateMyApi.getForObject(myUri, String.class);
		JSONArray jsonArr2 = new JSONArray(myResult);
		
		assertEquals(jsonArr1.length(), jsonArr2.length());
		
	}

	@Test
	@Order(2)
	public void shouldReturnCreatedPersonage() throws Exception {

		Personage personage = new Personage();
        personage.setName("Jane");

		String uri = "http://localhost:8080/addNewPersonage";

		RestTemplate restTemplateMyApi = new RestTemplate();
        Personage myResult = restTemplateMyApi.postForObject(uri, personage, Personage.class);

		assertEquals(personage.getName(), myResult.getName());

		String[] args = new String[]{"initParams"}; // To adapt
		ConfigurableApplicationContext ctx = SpringApplication.run(ApiHPApplication.class, args);
		ctx.close();
		
	}

}
