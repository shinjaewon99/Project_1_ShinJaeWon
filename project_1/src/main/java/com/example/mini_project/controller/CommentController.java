package com.example.mini_project.controller;

import com.example.mini_project.dto.comment.request.CommentCreateRequestDto;
import com.example.mini_project.dto.comment.request.CommentDeleteRequestDto;
import com.example.mini_project.dto.comment.request.CommentReplyRequestDto;
import com.example.mini_project.dto.comment.request.CommentUpdateRequestDto;
import com.example.mini_project.dto.comment.response.CommentPageResponseDto;
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
@RequestMapping("/user/{userId}/items/{itemId}/comments")
public class CommentController {
    private final CommentServiceImpl service;


    // 생성
    @PostMapping
    public ResponseEntity<Map<String, String>> create(@PathVariable Long userId,
                                                      @PathVariable Long itemId,
                                                      @RequestBody CommentCreateRequestDto dto) {
        service.create(userId, itemId, dto);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(MESSAGE, COMMENT_CREATE_MESSAGE);

        return ResponseEntity.ok(responseBody);
    }

    // 페이징 조회
    @GetMapping
    public Page<CommentPageResponseDto> readPage(@PathVariable Long userId,
                                                 @PathVariable Long itemId) {
        return service.readPage(userId, itemId);
    }


    // 수정
    @RequestMapping(value = "{commentId}", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, String>> update(@PathVariable Long userId,
                                                      @PathVariable Long itemId,
                                                      @PathVariable Long commentId,
                                                      @RequestBody CommentUpdateRequestDto dto) {

        service.update(userId, itemId, commentId, dto);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(MESSAGE, UPDATE_MESSAGE);


        return ResponseEntity.ok(responseBody);
    }

    // 댓글에 답변 추가
    @RequestMapping(value = "{commentId}/reply", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, String>> commentReply(@PathVariable Long userId,
                                                            @PathVariable Long itemId,
                                                            @PathVariable Long commentId,
                                                            @RequestBody CommentReplyRequestDto dto) {
        service.commentReply(userId, itemId, commentId, dto);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(MESSAGE, COMMENT_REPLY_MESSAGE);

        return ResponseEntity.ok(responseBody);
    }

    // 삭제
    @RequestMapping(value = "{commentId}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long userId,
                                                      @PathVariable Long itemId,
                                                      @PathVariable Long commentId,
                                                      @RequestBody CommentDeleteRequestDto dto) {

        service.delete(userId, itemId, commentId, dto);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(MESSAGE, DELETE_MESSAGE);
        return ResponseEntity.ok(responseBody);
    }
}
