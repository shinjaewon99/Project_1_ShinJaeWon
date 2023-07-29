package com.example.mini_project.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 무분별한 생성자 생성 방지
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // N 대 1 매핑 관계 : 하나의 상품에는 여러가지 제안을 할수 있음
    // X to One 관계에서는 LAZY를 해줘야 N+1 이슈 방지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_id")
    @JsonIgnore
    private SalesItemEntity salesItem;
    private String writer;
    private String password;
    private String content;
    private String reply;

    // 생성 메소드
    @Builder
    public static CommentEntity create(SalesItemEntity itemId, String writer, String password, String content) {
        CommentEntity entity = new CommentEntity();
        entity.salesItem = itemId;
        entity.writer = writer;
        entity.password = password;
        entity.content = content;

        return entity;
    }

    // 엔티티 setter를 막기위한 updateComment 메소드
    public void updateComment(String writer, String password, String content) {
        this.writer = writer;
        this.password = password;
        this.content = content;
    }

    // 엔티티 setter를 막기위한 commentReply 메소드
    public void commentReplyCreate(String reply) {
        this.reply = reply;
    }

    public void setItem(SalesItemEntity item) {
        this.salesItem = item;
    }
}
