package com.example.hello.validation.dto;

import com.example.hello.validation.annotation.Nickname;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class User2 {

    @NotBlank
    private String name;

    @Nickname(pattern = "^[0-9]*$")
    private String nickname;

    @Max(value = 90)
    private int age;

    //필드가 오브젝트 형태라면 @Valid를 붙혀줘야
    //해당 클래스에 타고 들어가 검증한다.
    @Valid
    private List<Car> cars;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User2{" +
                "name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", age=" + age +
                ", cars=" + cars +
                '}';
    }
}
