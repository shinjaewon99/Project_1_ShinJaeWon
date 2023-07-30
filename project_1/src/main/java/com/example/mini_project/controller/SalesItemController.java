package com.example.mini_project.controller;


import com.example.mini_project.dto.salesItem.request.ItemCreateRequestDto;
import com.example.mini_project.dto.salesItem.request.ItemDeleteRequestDto;
import com.example.mini_project.dto.salesItem.request.ItemUpdateRequestDto;
import com.example.mini_project.dto.salesItem.response.ItemPageResponseDto;
import com.example.mini_project.dto.salesItem.response.ItemReadOneResponseDto;
import com.example.mini_project.service.salesItem.SalesItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.example.mini_project.constant.CommonMessage.MESSAGE;
import static com.example.mini_project.constant.salesItem.SalesItemMessage.*;

@RestController
@RequestMapping("/user/{userId}/items")
@RequiredArgsConstructor
public class SalesItemController {
    private final SalesItemServiceImpl service;

    // 등록
    @PostMapping
    public ResponseEntity<Map<String, String>> create(@PathVariable Long userId, @RequestBody ItemCreateRequestDto dto) {
        service.create(userId, dto);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(MESSAGE, SIGN_UP_MESSAGE);

        return ResponseEntity.ok(responseBody);
    }

    // 단일 조회
    @GetMapping("{itemId}")
    public ItemReadOneResponseDto readOne(@PathVariable Long userId, @PathVariable("itemId") Long itemId) {
        return service.readOne(userId, itemId);
    }

    // 전체 조회
//    @GetMapping
//    public List<SalesItemDto> readAll(){
//        return service.readAll();
//    }


    // 페이징 조회
    @GetMapping
    public Page<ItemPageResponseDto> readPage(@PathVariable Long userId,
                                              @RequestParam("page") Integer page,
                                              @RequestParam("limit") Integer limit) {
        return service.readPage(userId, page, limit);
    }


    // 수정
    @RequestMapping(value = "{itemId}", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, String>> update(@PathVariable Long userId,
                                                      @PathVariable("itemId") Long itemId,
                                                      @RequestBody ItemUpdateRequestDto dto) {

        service.update(userId, itemId, dto);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(MESSAGE, UPDATE_MESSAGE);

        return ResponseEntity.ok(responseBody);
    }

    // 파일 업로드
    @RequestMapping(value = "{itemId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<Map<String, String>> uploadFile(@PathVariable Long userId,
                                                          @PathVariable("itemId") Long itemId,
                                                          @RequestParam("image") MultipartFile file,
                                                          ItemCreateRequestDto dto) throws IOException {

        service.uploadFile(userId, itemId, file, dto);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(MESSAGE, UPLOAD_IMAGE_MESSAGE);
        return ResponseEntity.ok(responseBody);
    }

    // 삭제 메소드
    @RequestMapping(value = "{itemId}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long userId,
                                                      @PathVariable("itemId") Long itemId,
                                                      @RequestBody ItemDeleteRequestDto dto) {
        service.delete(userId, itemId, dto);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(MESSAGE, DELETE_MESSAGE);
        return ResponseEntity.ok(responseBody);
    }
}
