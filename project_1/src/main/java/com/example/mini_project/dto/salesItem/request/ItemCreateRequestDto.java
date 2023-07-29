package com.example.mini_project.dto.salesItem.request;

import com.example.mini_project.entity.SalesItemEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCreateRequestDto extends ItemRequest{
    // 호출 할때마다 새로운 객체 생성을 막기위한 static 상수
    private static final ItemCreateRequestDto EXISTING_ITEM_CRETE_DTO = new ItemCreateRequestDto();

    public static ItemCreateRequestDto entityToDto(SalesItemEntity entity) {

        ItemCreateRequestDto dto = getItemCreteDto();

        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setMinPriceWanted(entity.getMinPriceWanted());
        dto.setStatus(entity.getStatus());
        dto.setWriter(entity.getWriter());
        dto.setPassword(dto.getPassword());

        return dto;
    }

    private static ItemCreateRequestDto getItemCreteDto() {
        return EXISTING_ITEM_CRETE_DTO;
    }
}