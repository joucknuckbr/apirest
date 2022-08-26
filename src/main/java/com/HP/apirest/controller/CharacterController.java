package com.HP.apirest.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.HP.apirest.model.Personage;
import com.HP.apirest.model.Wand;
import com.HP.apirest.service.PersonageService;

@RestController
public class CharacterController {
    
    @Autowired
    PersonageService personageService;
    
    @RequestMapping(value = "/pushAll", method = RequestMethod.GET)
    public List<Personage> pushAll() {
        
        if(personageService.verifyDatabase()) {
            return personageService.getAllPersonages();
        }
        
        String uri = "http://hp-api.herokuapp.com/api/characters";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        
        try{
            JSONArray jsonArr = new JSONArray(result);
            List<Personage> personages = new ArrayList<Personage>();
            
            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject obj = jsonArr.getJSONObject(i);
                Personage personageModel = new Personage();
                
                //name
                personageModel.setName(obj.getString("name"));
                
                //alternate_names
                String[] altNam = toStringArray(obj.getJSONArray("alternate_names"));
                personageModel.setAlternate_names(altNam);
                
                //species
                personageModel.setSpecies(obj.getString("species"));
                
                //gender
                personageModel.setGender(obj.getString("gender"));
                
                //house
                personageModel.setHouse(obj.getString("house"));
                
                //birth_date
                String birthDate = obj.getString("dateOfBirth");
                if(birthDate == "") {
                    personageModel.setDateOfBirth(null);
                } else { 
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date formatedBirthDate = formatter.parse(birthDate);               
                    personageModel.setDateOfBirth(formatedBirthDate);
                }
                
                //yearOfBirth
                if(obj.get("yearOfBirth").getClass().getName() == "java.lang.String") {
                    personageModel.setYearOfBirth(0);
                } else {
                    personageModel.setYearOfBirth(obj.getInt("yearOfBirth"));
                }
                
                //wizard
                personageModel.setWizard(obj.getBoolean("wizard"));
                
                //ancestry
                personageModel.setAncestry(obj.getString("ancestry"));
                
                //eyeColour
                personageModel.setEyeColour(obj.getString("eyeColour"));
                
                //hairColour
                personageModel.setHairColour(obj.getString("hairColour"));
                
                //wand
                Wand wand = new Wand();
                
                wand.setWood(obj.getJSONObject("wand").getString("wood"));
                wand.setCore(obj.getJSONObject("wand").getString("core"));
                //colocar sobre esse bug na documentação
                switch(obj.getJSONObject("wand").get("length").getClass().getName()) {
                    case "java.lang.String": {
                        wand.setLength(0.0);
                        break;
                    }
                    case "java.lang.Integer": {
                        Integer aux = obj.getJSONObject("wand").getInt("length");
                        wand.setLength(Double.valueOf(aux));
                        break;
                    }
                    default: {
                        wand.setLength(obj.getJSONObject("wand").getDouble("length"));
                        break;
                    }
                }
                
                personageModel.setWand(wand);
                
                //patronus
                personageModel.setPatronus(obj.getString("patronus"));
                
                //hogwartsStudent
                personageModel.setHogwartsStudent(obj.getBoolean("hogwartsStudent"));
                
                //hogwartsStaff
                personageModel.setHogwartsStaff(obj.getBoolean("hogwartsStaff"));
                
                //actor
                personageModel.setActor(obj.getString("actor"));
                
                //alternate_actors
                String[] altAct = toStringArray(obj.getJSONArray("alternate_actors"));
                personageModel.setAlternate_actors(altAct);
                
                //alive
                personageModel.setAlive(obj.getBoolean("alive"));
                
                //image
                personageModel.setImage(obj.getString("image"));  
                
                personageService.addNewPersonage(personageModel);
                
                personages.add(personageModel);
            }
            
            return personages;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    @RequestMapping(value = "/showAll", method = RequestMethod.GET)
    public List<Personage> showAll() {
        
        return personageService.getAllPersonages();
        
    }

    @RequestMapping(value = "/addNewPersonage", method = RequestMethod.POST)
    public Personage addNewPersonage(@Validated @RequestBody Personage personage) {
        
        try{
            personageService.addNewPersonage(personage);

            return personage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }

    @RequestMapping(value = "/deletePersonage", method = RequestMethod.DELETE)
    public String deletePersonage(Long id) {
        
        try{
            personageService.deletePersonage(id);

            return "Removed";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    public static String[] toStringArray(JSONArray array) {
        if(array==null)
        return new String[0];
        
        String[] arr = new String[array.length()];
        for(int i = 0; i < arr.length; i++) {
            arr[i]=array.optString(i);
        }
        return arr;
    }
    
}
