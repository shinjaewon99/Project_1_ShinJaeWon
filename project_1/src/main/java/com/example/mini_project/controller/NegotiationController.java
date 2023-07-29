package com.example.mini_project.controller;

import com.example.mini_project.dto.ResponseDto;
import com.example.mini_project.dto.negotiation.request.NegotiationCreateRequestDto;
import com.example.mini_project.dto.negotiation.request.NegotiationDeleteRequestDto;
import com.example.mini_project.dto.negotiation.request.NegotiationUpdateRequestDto;
import com.example.mini_project.dto.negotiation.response.NegotiationPageResponseDto;
import com.example.mini_project.service.negotiation.NegotiationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.example.mini_project.constant.CommonMessage.MESSAGE;
import static com.example.mini_project.constant.negotiation.NegotiationMessage.CREATE_MESSAGE;
import static com.example.mini_project.constant.negotiation.NegotiationMessage.DELETE_MESSAGE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/{userId}/items/{itemId}/proposals")
public class NegotiationController {
    private final NegotiationServiceImpl service;
    ResponseDto message = new ResponseDto();

    // 생성
    @PostMapping
    public ResponseEntity<Map<String, String>> create(@PathVariable Long userId,
                                                      @PathVariable Long itemId,
                                                      @RequestBody NegotiationCreateRequestDto dto) {
        service.create(userId, itemId, dto);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(MESSAGE, CREATE_MESSAGE);
        return ResponseEntity.ok(responseBody);
    }

    // 페이징 조회
    @GetMapping
    public Page<NegotiationPageResponseDto> readPage(@PathVariable Long userId,
                                                     @PathVariable("itemId") Long itemId, @RequestParam String writer,
                                                     @RequestParam String password, @RequestParam Integer page) {
        return service.readPage(userId, itemId, writer, password, page);
    }


    // 수정
    // 상태 변경
    @RequestMapping(value = {"{proposalId}"}, method = RequestMethod.PUT)
    public ResponseEntity<Map<String, String>> update(@PathVariable Long userId,
                                                      @PathVariable Long itemId,
                                                      @PathVariable Long proposalId,
                                                      @RequestBody NegotiationUpdateRequestDto dto) {

        service.updateProposal(userId, itemId, proposalId, dto, message);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(MESSAGE, message.getMessage());
        return ResponseEntity.ok(responseBody);
    }

    // 삭제
    @RequestMapping(value = {"{proposalId}"}, method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long userId,
                                                      @PathVariable Long itemId,
                                                      @PathVariable Long proposalId,
                                                      @RequestBody NegotiationDeleteRequestDto dto) {

        service.delete(userId, itemId, proposalId, dto);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(MESSAGE, DELETE_MESSAGE);
        return ResponseEntity.ok(responseBody);
    }
}
