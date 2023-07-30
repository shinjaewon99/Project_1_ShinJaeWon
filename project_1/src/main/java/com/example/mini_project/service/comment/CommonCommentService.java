package com.example.mini_project.service.comment;

import com.example.mini_project.dto.comment.response.CommentPageResponseDto;
import org.springframework.data.domain.Page;

public interface CommonCommentService {
    Page<CommentPageResponseDto> readPage(Long itemId);
}
