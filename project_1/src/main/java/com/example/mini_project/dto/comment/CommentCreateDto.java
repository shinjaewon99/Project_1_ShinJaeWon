package com.example.mini_project.dto.comment;

import com.example.mini_project.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateDto {
    private String content;
    private String writer;
    private String password;

    public static CommentCreateDto entityToDto(CommentEntity entity) {

        CommentCreateDto dto = new CommentCreateDto();

        dto.setContent(entity.getContent());
        dto.setWriter(entity.getWriter());
        dto.setPassword(entity.getPassword());

        return dto;
    }
}
