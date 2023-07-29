package com.example.mini_project.dto.comment.request;

import com.example.mini_project.dto.comment.request.base.CommentRequest;
import com.example.mini_project.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUpdateRequestDto extends CommentRequest {
    // 호출 할때마다 새로운 객체 생성을 막기위한 static 상수
    private static final CommentUpdateRequestDto EXISTING_COMMENT_UPDATE_DTO = new CommentUpdateRequestDto();

    public static CommentUpdateRequestDto entityToDto(CommentEntity entity) {

        CommentUpdateRequestDto dto = getCommentUpdateDto();

        dto.setContent(entity.getContent());
        dto.setWriter(entity.getWriter());
        dto.setPassword(entity.getPassword());

        return dto;
    }

    private static CommentUpdateRequestDto getCommentUpdateDto() {
        return EXISTING_COMMENT_UPDATE_DTO;
    }
}