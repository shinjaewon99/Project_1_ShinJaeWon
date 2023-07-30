package com.example.mini_project.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 무분별한 생성자 생성 방지
public class NegotiationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private SalesItemEntity salesItem;
    private Integer suggestedPrice;
    private String status;
    private String writer;
    private String password;

    // 연관관계 편의 메소드 (중복을 방지)
    public void setItem(SalesItemEntity salesItem) {
        this.salesItem = salesItem;
        salesItem.getNegotiations().add(this);
    }

    // 생성 메소드
    @Builder
    public static NegotiationEntity create(SalesItemEntity itemId,
                                           String writer, String password, Integer suggestedPrice, String status) {
        NegotiationEntity entity = new NegotiationEntity();

        entity.setItem(itemId);
        entity.writer = writer;
        entity.password = password;
        entity.suggestedPrice = suggestedPrice;
        entity.status = status;

        return entity;
    }

    // 수정 메소드
    public void updateProposal(String writer, String password, Integer suggestedPrice) {
        this.writer = writer;
        this.password = password;
        this.suggestedPrice = suggestedPrice;
    }

    // 상태 변경 메소드
    public void updateStatus(String status) {
        this.status = status;
    }
}
