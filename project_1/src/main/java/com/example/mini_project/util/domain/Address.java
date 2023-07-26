package com.example.mini_project.util.domain;

import jakarta.persistence.Embeddable;

/*
@Embeddable : Column을 하나의 객체로 사용하기위해 사용
현재 코드에서는 "주소" 라는 객체로 묶어 표현하면 보다 객체지향적이며 가독성을 높일수 있습니다.
@Embeddable (값 타임을 "정의"하는 곳에 사용), @Embedded(값 타임을 "사용"하는곳에 사용) 사용시 기본생성자는 필수 입니다.
 */
@Embeddable
public class Address {
    private String city;
    private String country;

    // public을 하게되면 변동이 될수 있음으로, 접근 지정자를 protected로 설정
    protected Address() {
    }

    public Address(String city, String country) {
        this.city = city;
        this.country = country;
    }
}
