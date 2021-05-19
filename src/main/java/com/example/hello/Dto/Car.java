package com.example.hello.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;



public class Car {

    private String name;
    @JsonProperty("car_num")
    private String carNum;


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

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", carNum='" + carNum + '\'' +
                '}';
    }
}
