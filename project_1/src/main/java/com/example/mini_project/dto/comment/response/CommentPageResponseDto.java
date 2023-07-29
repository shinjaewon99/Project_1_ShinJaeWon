package com.example.mini_project.dto.comment.response;

import com.example.mini_project.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentPageResponseDto {
    // 호출 할때마다 새로운 객체 생성을 막기위한 static 상수
    private static final CommentPageResponseDto EXISTING_COMMENT_PAGE_DTO = new CommentPageResponseDto();


    private Long id;
    private String content;
    private String reply;

    public static CommentPageResponseDto pageResponse(CommentEntity entity) {
        CommentPageResponseDto dto = getCommentUpdateDto();

        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setReply(entity.getReply());

        return dto;
    }

    private static CommentPageResponseDto getCommentUpdateDto() {
        return EXISTING_COMMENT_PAGE_DTO;
    }
}
