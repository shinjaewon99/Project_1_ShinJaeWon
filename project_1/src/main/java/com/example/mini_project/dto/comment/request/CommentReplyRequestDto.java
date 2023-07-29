package com.example.mini_project.dto.comment.request;

import com.example.mini_project.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentReplyRequestDto {
    // 호출 할때마다 새로운 객체 생성을 막기위한 static 상수
    private static final CommentReplyRequestDto EXISTING_COMMENT_REPLY_DTO = new CommentReplyRequestDto();

    private String writer;
    private String password;
    private String reply;

    public static CommentReplyRequestDto entityToDto(CommentEntity entity) {
        CommentReplyRequestDto dto = getCommentReplyDto();

        dto.setWriter(entity.getWriter());
        dto.setPassword(entity.getPassword());
        dto.setReply(entity.getReply());

        return dto;
    }

    private static CommentReplyRequestDto getCommentReplyDto() {
        return EXISTING_COMMENT_REPLY_DTO;
    }
}
