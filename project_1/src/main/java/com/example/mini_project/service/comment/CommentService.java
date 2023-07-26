package com.example.mini_project.service.comment;

import com.example.mini_project.dto.comment.CommentCreateDto;
import com.example.mini_project.dto.comment.CommentPageResponseDto;
import com.example.mini_project.dto.comment.CommentReplyDto;
import com.example.mini_project.entity.CommentEntity;
import com.example.mini_project.entity.SalesItemEntity;
import org.springframework.data.domain.Page;

public interface CommentService {
    CommentCreateDto create(Long itemId, CommentCreateDto dto);

    Page<CommentPageResponseDto> readPage(Long itemId);

    void update(Long itemId, Long commentId, CommentCreateDto dto);

    void commentReply(Long itemId, Long commentId, CommentReplyDto dto);

    void delete(Long itemId, Long commentId, CommentCreateDto dto);

    SalesItemEntity validateExistItemId(Long itemId);

    CommentEntity validateExistCommentId(Long commentId);
}
