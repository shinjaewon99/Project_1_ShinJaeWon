package com.example.mini_project.dto.comment;

import com.example.mini_project.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentPageResponseDto {

    private Long id;
    private String content;
    private String reply;

    public static CommentPageResponseDto pageResponse(CommentEntity entity) {
        CommentPageResponseDto dto = new CommentPageResponseDto();

        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setReply(entity.getReply());

        return dto;
    }
}
