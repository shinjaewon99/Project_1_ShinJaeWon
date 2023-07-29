package com.example.mini_project.dto.comment.request;

import com.example.mini_project.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDeleteRequestDto {
    // 호출 할때마다 새로운 객체 생성을 막기위한 static 상수
    private static final CommentDeleteRequestDto EXISTING_COMMENT_DELETE_DTO = new CommentDeleteRequestDto();
    private String writer;
    private String password;

    public static CommentDeleteRequestDto entityToDto(CommentEntity entity) {
        CommentDeleteRequestDto dto = getCommentDeleteDto();

        dto.setWriter(entity.getWriter());
        dto.setPassword(entity.getPassword());

        return dto;
    }

    private static CommentDeleteRequestDto getCommentDeleteDto(){
        return EXISTING_COMMENT_DELETE_DTO;
    }
}
