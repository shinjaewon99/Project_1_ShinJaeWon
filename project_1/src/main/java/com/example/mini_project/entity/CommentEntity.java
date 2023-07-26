package com.example.mini_project.entity;


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

    private Long itemId;
    private String writer;
    private String password;
    private String content;
    private String reply;


    // N 대 1 매핑 관계 : 하나의 상품에는 여러가지 제안을 할수 있음
    // X to One 관계에서는 LAZY를 해줘야 N+1 이슈 방지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_id")
    private SalesItemEntity salesItem;


    // 생성 메소드
    @Builder
    public static CommentEntity create(Long itemId, String writer, String password, String content) {
        CommentEntity entity = new CommentEntity();
        entity.itemId = itemId;
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
}
