package com.example.mini_project.repository;

import com.example.mini_project.entity.SalesItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 스프링 데이터 JPA 에서는 @Repository 생략 가능
public interface SalesItemRepository extends JpaRepository<SalesItemEntity, Long> {


}
