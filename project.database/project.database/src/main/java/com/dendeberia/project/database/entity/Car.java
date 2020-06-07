package com.dendeberia.project.database.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String make;
    private String model;
    private String type;
    private String imgPath;

    public Car(Long id, String make, String model, String type, String imgPath){
        this.id = id;
        this.make = make;
        this.model = model;
        this.type = type;
        this.imgPath = imgPath;
    }

    public Car(String make, String model, String type, String imgPath){
        this.make = make;
        this.model = model;
        this.type = type;
        this.imgPath = imgPath;
    }



    public Car(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
