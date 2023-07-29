package com.example.mini_project.dto.salesItem.request;

import com.example.mini_project.entity.SalesItemEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemUpdateRequestDto extends ItemRequest{
    // 호출 할때마다 새로운 객체 생성을 막기위한 static 상수
    private static final ItemUpdateRequestDto EXISTING_ITEM_UPDATE_DTO = new ItemUpdateRequestDto();

    public static ItemUpdateRequestDto entityToDto(SalesItemEntity entity) {

        ItemUpdateRequestDto dto = getItemUpdateDto();

        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setMinPriceWanted(entity.getMinPriceWanted());
        dto.setStatus(entity.getStatus());
        dto.setWriter(entity.getWriter());
        dto.setPassword(dto.getPassword());

        return dto;
    }

    private static ItemUpdateRequestDto getItemUpdateDto() {
        return EXISTING_ITEM_UPDATE_DTO;
    }
}