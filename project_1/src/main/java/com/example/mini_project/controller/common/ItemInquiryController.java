package com.example.mini_project.controller.common;

import com.example.mini_project.dto.salesItem.response.ItemPageResponseDto;
import com.example.mini_project.dto.salesItem.response.ItemReadOneResponseDto;
import com.example.mini_project.service.salesItem.SalesItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("items")
@RequiredArgsConstructor
public class ItemInquiryController {
    private final SalesItemServiceImpl salesItemService;

    // 단일 조회
    @GetMapping("/{itemId}")
    public ItemReadOneResponseDto readOne(@PathVariable("itemId") Long itemId) {
        return salesItemService.readOne(itemId);
    }

    // 페이징 조회
    @GetMapping
    public Page<ItemPageResponseDto> readPage(@RequestParam("page") Integer page,
                                              @RequestParam("limit") Integer limit) {
        return salesItemService.readPage(page, limit);
    }
}
