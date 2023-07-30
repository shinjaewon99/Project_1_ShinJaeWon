package com.example.mini_project.controller.common;

import com.example.mini_project.dto.comment.response.CommentPageResponseDto;
import com.example.mini_project.service.comment.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items/{itemId}/comments")
@RequiredArgsConstructor
public class CommentInquiryController {
    private final CommentServiceImpl commentService;

    // 페이징 조회
    @GetMapping
    public Page<CommentPageResponseDto> readPage(@PathVariable Long itemId) {
        return commentService.readPage(itemId);
    }
}
