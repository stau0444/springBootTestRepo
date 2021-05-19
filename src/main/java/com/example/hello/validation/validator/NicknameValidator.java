package com.example.hello.validation.validator;


import com.example.hello.validation.annotation.Nickname;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
validator 생성 방법

1.ConstraintValidator를 구현한다 generic type 으로는 구동시킬려는 어노테이션과 , 들어오는 벨류의 타입을 적는다.
2.initialize 메서드 에서는 해당 어노테이션으로 부터 들어온 정보들을 초기화 시킬 수 있다.
3.isValid 메서드 에서는 실제 검증 로직이 들어간다 .
*/




public class NicknameValidator implements ConstraintValidator<Nickname, String> {

    private String pattern;

    @Override
    public void initialize(Nickname constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        String defaultConstraintMessageTemplate = context.getDefaultConstraintMessageTemplate();
        System.out.println(defaultConstraintMessageTemplate);
        boolean matches = Pattern.matches(pattern, value);
        if(!matches){
            return false;
        }
        return true;

    }
}
