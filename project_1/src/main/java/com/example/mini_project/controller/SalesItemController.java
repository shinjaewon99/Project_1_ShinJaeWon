package com.example.mini_project.controller;


import com.example.mini_project.dto.salesItem.SalesItemCreateDto;
import com.example.mini_project.dto.salesItem.SalesItemPageResponseDto;
import com.example.mini_project.dto.salesItem.SalesItemReadOneDto;
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
@RequestMapping("/items")
@RequiredArgsConstructor
public class SalesItemController {
    private final SalesItemServiceImpl service;

    // 등록
    @PostMapping
    public ResponseEntity<Map<String, String>> create(@RequestBody SalesItemCreateDto dto) {
        service.create(dto);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(MESSAGE, SIGN_UP_MESSAGE);

        return ResponseEntity.ok(responseBody);
    }

    // 단일 조회
    @GetMapping("{itemId}")
    public SalesItemReadOneDto readOne(@PathVariable("itemId") Long itemId) {
        return service.readOne(itemId);
    }

    // 전체 조회
//    @GetMapping
//    public List<SalesItemDto> readAll(){
//        return service.readAll();
//    }


    // 페이징 조회
    @GetMapping
    public Page<SalesItemPageResponseDto> readPage(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return service.readPage(page, limit);
    }


    // 수정
    @RequestMapping(value = "{itemId}", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, String>> update(@PathVariable("itemId") Long itemId, @RequestBody SalesItemCreateDto dto) {

        service.update(itemId, dto);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(MESSAGE, UPDATE_MESSAGE);

        return ResponseEntity.ok(responseBody);
    }

    // 파일 업로드
    @RequestMapping(value = "{itemId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<Map<String, String>> uploadFile(@PathVariable("itemId") Long itemId,
                                                          @RequestParam("image") MultipartFile file,
                                                          SalesItemCreateDto dto) throws IOException {

        service.uploadFile(itemId, file, dto);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(MESSAGE, UPLOAD_IMAGE_MESSAGE);
        return ResponseEntity.ok(responseBody);
    }

    // 삭제 메소드
    @RequestMapping(value = "{itemId}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, String>> delete(@PathVariable("itemId") Long itemId, @RequestBody SalesItemCreateDto dto) {
        service.delete(itemId, dto);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(MESSAGE, DELETE_MESSAGE);
        return ResponseEntity.ok(responseBody);
    }
}
