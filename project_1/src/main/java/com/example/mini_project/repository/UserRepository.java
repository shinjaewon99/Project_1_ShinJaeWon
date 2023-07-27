package com.example.mini_project.repository;

import com.example.mini_project.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserId(String userId); // user 아이디 찾는 쿼리메소드
    Boolean existsByUserId(String userId); // user 아이디 존재 여부 쿼리 메소드
}
