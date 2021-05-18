package com.example.hello.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObjectMapperUser {

    private String name;
    private int age;
    @JsonProperty("phone_num")
    private String phoneNum;

    public ObjectMapperUser(){};

    public ObjectMapperUser(String name, int age, String phoneNum) {
        this.name = name;
        this.age = age;
        this.phoneNum = phoneNum;
    }

    //해당 dto 안에 get의 이름이 붙은 메서드는 다 참조하기 떄문에
    //getter 메서드가 아닌 메서드는 get을 사용하지 않아야 한다.
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "ObjectMapperUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
