package com.example.mini_project.dto.comment.request;

import com.example.mini_project.dto.comment.request.base.CommentRequest;
import com.example.mini_project.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateRequestDto extends CommentRequest {
    // 호출 할때마다 새로운 객체 생성을 막기위한 static 상수
    private static final CommentCreateRequestDto EXISTING_COMMENT_CRETE_DTO = new CommentCreateRequestDto();

    public static CommentCreateRequestDto entityToDto(CommentEntity entity) {

        CommentCreateRequestDto dto = getCommentCreteDto();

        dto.setContent(entity.getContent());
        dto.setWriter(entity.getWriter());
        dto.setPassword(entity.getPassword());

        return dto;
    }

    private static CommentCreateRequestDto getCommentCreteDto() {
        return EXISTING_COMMENT_CRETE_DTO;
    }
}