package com.example.mini_project.dto.salesItem.request;

import com.example.mini_project.entity.SalesItemEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDeleteRequestDto extends ItemRequest{
    // 호출 할때마다 새로운 객체 생성을 막기위한 static 상수
    private static final ItemDeleteRequestDto EXISTING_ITEM_DELETE_DTO = new ItemDeleteRequestDto();

    public static ItemDeleteRequestDto entityToDto(SalesItemEntity entity) {

        ItemDeleteRequestDto dto = getItemDeleteDto();

        dto.setWriter(entity.getWriter());
        dto.setPassword(dto.getPassword());

        return dto;
    }

    private static ItemDeleteRequestDto getItemDeleteDto() {
        return EXISTING_ITEM_DELETE_DTO;
    }
}