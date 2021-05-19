package com.example.ObjectMapperTest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Car {

    private String name;
    @JsonProperty("car_num")
    private String carNum;
    @JsonProperty("TYPE")
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", carNum='" + carNum + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
