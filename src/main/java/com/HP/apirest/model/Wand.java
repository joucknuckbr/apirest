package com.HP.apirest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Wand {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String wood;
    private String core;
    private Double length;
    
    public Wand() {
        
    }
    
    public String getWood() {
        return wood;
    }
    public void setWood(String wood) {
        this.wood = wood;
    }
    public String getCore() {
        return core;
    }
    public void setCore(String core) {
        this.core = core;
    }
    public Double getLength() {
        return length;
    }
    public void setLength(Double length) {
        this.length = length;
    }
    
}