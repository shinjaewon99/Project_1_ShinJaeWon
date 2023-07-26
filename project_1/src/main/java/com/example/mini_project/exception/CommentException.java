package com.example.mini_project.exception;

import com.example.mini_project.dto.comment.CommentCreateDto;
import com.example.mini_project.dto.comment.CommentReplyDto;
import com.example.mini_project.entity.CommentEntity;
import com.example.mini_project.entity.SalesItemEntity;

import static com.example.mini_project.constant.comment.CommentMessage.*;

public class CommentException {

    // 댓글 생성 검증 메소드
    public void validateCreateException(CommentCreateDto dto) {
        if (dto.getWriter() == null || dto.getPassword() == null
                || dto.getContent() == null) {
            throw new IllegalArgumentException(VALIDATE_CREATE_MESSAGE);
        }
    }

    // 댓글의 작성자 혹은 비밀번호 검증 메소드
    public void validateWriterOrPassword(CommentEntity entity, CommentCreateDto dto) {
        if (!entity.getWriter().equals(dto.getWriter()) || !entity.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException(NOT_SAME_WRITER_PASSWORD_MESSAGE);
        }
    }

    // 등록된 상품에 대한 작성자 혹은 비밀번호 검증 메소드
    public void validateWriterOrPasswordFromSalesItem(SalesItemEntity findItem, CommentReplyDto dto) {
        if (!findItem.getWriter().equals(dto.getWriter())
                || !findItem.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException(POST_ITEM_MISS_MATCH_WRITER_PASSWORD);
        }
    }
}
