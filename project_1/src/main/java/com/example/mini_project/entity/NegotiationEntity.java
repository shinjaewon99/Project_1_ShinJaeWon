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

    private Long itemId;
    private Integer suggestedPrice;
    private String status;
    private String writer;
    private String password;

    // N 대 1 매핑 관계 : 하나의 상품에는 여러가지 제안을 할수 있음
    // X to One 관계에서는 LAZY를 해줘야 N+1 이슈 방지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_id")
    private SalesItemEntity salesItem;

    // 생성 메소드
    @Builder
    public static NegotiationEntity create(String writer, String password, Integer suggestedPrice, String status) {
        NegotiationEntity entity = new NegotiationEntity();

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
