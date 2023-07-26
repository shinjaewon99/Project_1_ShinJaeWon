package com.example.mini_project.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 무분별한 생성자 생성 방지
public class SalesItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_id")
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Integer minPriceWanted;
    private String status;
    private String writer;
    private String password;

    @OneToMany(mappedBy = "salesItem")
    private List<CommentEntity> comments = new ArrayList<>();

    @OneToMany(mappedBy = "salesItem")
    private List<NegotiationEntity> negotiations = new ArrayList<>();

    // 생성 메소드
    @Builder
    public static SalesItemEntity create(String title, String description, Integer minPriceWanted,
                                         String saleMessage, String writer, String password) {

        SalesItemEntity entity = new SalesItemEntity();

        entity.title = title;
        entity.description = description;
        entity.minPriceWanted = minPriceWanted;
        entity.status = saleMessage;
        entity.writer = writer;
        entity.password = password;

        return entity;
    }

    // 수정 메소드
    public void updateItem(String title, String description,
                           Integer minPriceWanted, String writer,
                           String password) {

        this.title = title;
        this.description = description;
        this.minPriceWanted = minPriceWanted;
        this.writer = writer;
        this.password = password;
    }

    // 파일 업로드 메소드
    public void uploadFile(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // 상태 갱신 메소드
    public void updateStatus(String status) {
        this.status = status;
    }
}
