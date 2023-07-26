package com.example.mini_project.repository;

import com.example.mini_project.entity.NegotiationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NegotiationRepository extends JpaRepository<NegotiationEntity, Long> {
    Page<NegotiationEntity> findAllByWriterLikeAndPasswordLike(String writer, String password, Pageable pageable);
}