package com.example.mini_project.entity.base;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/*
@MappedSuperclass // 엔티티 별로 공통 필드가 존재하는 경우 불 필요한 중복 코드를 제거하기 위해 사용한다.
@EntityListeners(value = AuditingEntityListener.class) // DB적용 전 공통부분을 등록하기 위해 등록한다.
@Getter
// 해당 클래스를 단독으로 사용할 일이 없기때문에 추상클래스로 선언한다.
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
*/
