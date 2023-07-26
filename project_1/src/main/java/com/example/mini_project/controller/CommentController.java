package com.example.mini_project.controller;

import com.example.mini_project.dto.comment.CommentCreateDto;
import com.example.mini_project.dto.comment.CommentPageResponseDto;
import com.example.mini_project.dto.comment.CommentReplyDto;
import com.example.mini_project.service.comment.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.example.mini_project.constant.CommonMessage.MESSAGE;
import static com.example.mini_project.constant.comment.CommentMessage.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items/{itemId}/comments")
public class CommentController {
    private final CommentServiceImpl service;


    // 생성
    @PostMapping
    public ResponseEntity<Map<String, String>> create(@PathVariable Long itemId,
                                                      @RequestBody CommentCreateDto dto) {
        service.create(itemId, dto);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(MESSAGE, COMMENT_CREATE_MESSAGE);

        return ResponseEntity.ok(responseBody);
    }

    // 페이징 조회
    @GetMapping
    public Page<CommentPageResponseDto> readPage(@PathVariable Long itemId) {
        return service.readPage(itemId);
    }


    // 수정
    @RequestMapping(value = "{commentId}", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, String>> update(@PathVariable Long itemId,
                                                      @PathVariable Long commentId,
                                                      @RequestBody CommentCreateDto dto) {

        service.update(itemId, commentId, dto);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(MESSAGE, UPDATE_MESSAGE);


        return ResponseEntity.ok(responseBody);
    }

    // 댓글에 답변 추가
    @RequestMapping(value = "{commentId}/reply", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, String>> commentReply(@PathVariable Long itemId,
                                                            @PathVariable Long commentId,
                                                            @RequestBody CommentReplyDto dto) {
        service.commentReply(itemId, commentId, dto);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(MESSAGE, COMMENT_REPLY_MESSAGE);

        return ResponseEntity.ok(responseBody);
    }

    // 삭제
    @RequestMapping(value = "{commentId}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long itemId,
                                                      @PathVariable Long commentId,
                                                      @RequestBody CommentCreateDto dto) {

        service.delete(itemId, commentId, dto);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(MESSAGE, DELETE_MESSAGE);
        return ResponseEntity.ok(responseBody);
    }
}
