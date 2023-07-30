package com.example.mini_project.service.salesItem;

import com.example.mini_project.dto.salesItem.response.ItemPageResponseDto;
import com.example.mini_project.dto.salesItem.response.ItemReadOneResponseDto;
import org.springframework.data.domain.Page;

public interface CommonSalesItemService {
    ItemReadOneResponseDto readOne(Long itemId);

    Page<ItemPageResponseDto> readPage(Integer page, Integer limit);
}
