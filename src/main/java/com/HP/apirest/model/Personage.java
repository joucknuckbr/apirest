package com.HP.apirest.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Personage {

    @Id
	@GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String[] alternate_names;
    private String species;
    private String gender;
    private String house;
    private Date dateOfBirth;
    private int yearOfBirth; 
    private boolean wizard;
    private String ancestry;
    private String eyeColour;
    private String hairColour;

    @OneToOne(targetEntity = Wand.class, fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
    private Wand wand;
    
    private String patronus;
    private boolean hogwartsStudent;
    private boolean hogwartsStaff;
    private String actor;
    private String[] alternate_actors;
    private boolean alive;
    private String image;

    public Personage() {   

    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String[] getAlternate_names() {
        return alternate_names;
    }
    public void setAlternate_names(String[] alternate_names) {
        this.alternate_names = alternate_names;
    }
    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getHouse() {
        return house;
    }
    public void setHouse(String house) {
        this.house = house;
    }
    public String getDateOfBirth() {

        if (dateOfBirth == null) {
            return "";
        }

        DateFormat outputFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String output = outputFormatter.format(dateOfBirth);

        return output;

    }
    public void setDateOfBirth(Date dateOfBirth) {

        this.dateOfBirth = dateOfBirth;
    }
    public int getYearOfBirth() {
        return yearOfBirth;
    }
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
    public boolean isWizard() {
        return wizard;
    }
    public void setWizard(boolean wizard) {
        this.wizard = wizard;
    }
    public String getAncestry() {
        return ancestry;
    }
    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }
    public String getEyeColour() {
        return eyeColour;
    }
    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }
    public String getHairColour() {
        return hairColour;
    }
    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }
    public Wand getWand() {
        return wand;
    }
    public void setWand(Wand wand) {
        this.wand = wand;
    }
    public String getPatronus() {
        return patronus;
    }
    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }
    public boolean isHogwartsStudent() {
        return hogwartsStudent;
    }
    public void setHogwartsStudent(boolean hogwartsStudent) {
        this.hogwartsStudent = hogwartsStudent;
    }
    public boolean isHogwartsStaff() {
        return hogwartsStaff;
    }
    public void setHogwartsStaff(boolean hogwartsStaff) {
        this.hogwartsStaff = hogwartsStaff;
    }
    public String getActor() {
        return actor;
    }
    public void setActor(String actor) {
        this.actor = actor;
    }
    public String[] getAlternate_actors() {
        return alternate_actors;
    }
    public void setAlternate_actors(String[] alternate_actors) {
        this.alternate_actors = alternate_actors;
    }
    public boolean isAlive() {
        return alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

}
