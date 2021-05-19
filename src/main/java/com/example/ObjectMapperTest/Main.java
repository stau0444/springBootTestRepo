package com.example.ObjectMapperTest;


import com.example.ObjectMapperTest.dto.Car;
import com.example.ObjectMapperTest.dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String [] args) throws JsonProcessingException {

        // 스프링 프로젝트가 아닌경우
        // Json데이터는 default 가 utf-8 이기 때문에
        // 프로젝트 셋팅을 UTF 8로 맞춰 줘야
        // 데이터가 꺠지지 않고 넘어간다.
        // 스프링프로젝트는 기본으로 UTF-8로 맞춰져있다.
        // 맥 환경일경우 기본이 utf-8 이므로 신경쓰지 않아도 된다.
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User();
        user.setName("ugo");
        user.setAge(20);

        Car car = new Car();
        car.setName("K5");
        car.setCarNum("14고 1233");
        car.setType("sedan");

        Car car2 = new Car();
        car2.setName("sorento");
        car2.setCarNum("22고 2222");
        car2.setType("suv");

        List<Car> carList = Arrays.asList(car,car2);

        user.setCars(carList);
        System.out.println(user);

        String jsonUser = objectMapper.writeValueAsString(user);
        //자바에서 prinln은 한줄로 나오기 떄문에 json 데이터 가독성이 낮다.
        //https://jsonformatter.curiousconcept.com/ 에서 json 포맷으로 변환 가능하다.
        System.out.println(jsonUser);

        JsonNode jsonNode = objectMapper.readTree(jsonUser);
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();
        System.out.println("name:"+_name);
        System.out.println("age:"+_age);


        //User에 List<Car>는 array 타입이기 때문에 형변환이 필요
        //String _list = jsonNode.get("car").asText();
        //System.out.println(_list);
        JsonNode cars = jsonNode.get("cars");

        //Array Node로 변환
        ArrayNode arrayNode = (ArrayNode) cars;
        System.out.println("arrayNode= ==" +arrayNode);

        //ArrayNode를 Car타입 List 형식으로 변환하였다.
        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {});
        System.out.println(_cars);


        //특정 json의 값을 바꾸고 싶은 경우
        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name", "경욱");
        objectNode.put("age",30);

        System.out.println(objectNode.toPrettyString());
     }
}
