package com.example.mini_project.dto.comment;

import com.example.mini_project.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentReplyDto {
    private String writer;
    private String password;
    private String reply;

    public static CommentReplyDto entityToDto(CommentEntity entity) {
        CommentReplyDto dto = new CommentReplyDto();

        dto.setWriter(entity.getWriter());
        dto.setPassword(entity.getPassword());
        dto.setReply(entity.getReply());

        return dto;
    }
}
