package com.example.mini_project.service.comment;

import com.example.mini_project.dto.comment.request.CommentCreateRequestDto;
import com.example.mini_project.dto.comment.request.CommentDeleteRequestDto;
import com.example.mini_project.dto.comment.request.CommentReplyRequestDto;
import com.example.mini_project.dto.comment.request.CommentUpdateRequestDto;
import com.example.mini_project.dto.comment.response.CommentPageResponseDto;
import com.example.mini_project.entity.CommentEntity;
import com.example.mini_project.entity.SalesItemEntity;
import com.example.mini_project.entity.UserEntity;
import org.springframework.data.domain.Page;

public interface CommentService {
    CommentCreateRequestDto create(Long userId, Long itemId, CommentCreateRequestDto dto);

    Page<CommentPageResponseDto> readPage(Long userId, Long itemId);

    void update(Long userId, Long itemId, Long commentId, CommentUpdateRequestDto dto);

    void commentReply(Long userId, Long itemId, Long commentId, CommentReplyRequestDto dto);

    void delete(Long userId, Long itemId, Long commentId, CommentDeleteRequestDto dto);

    SalesItemEntity validateExistItemId(Long itemId);

    CommentEntity validateExistCommentId(Long commentId);

    UserEntity validateExistUserId(Long userId);
}
