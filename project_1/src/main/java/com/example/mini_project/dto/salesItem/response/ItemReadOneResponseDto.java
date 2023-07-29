package com.example.mini_project.dto.salesItem.response;

import com.example.mini_project.entity.SalesItemEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemReadOneResponseDto {
    // 호출 할때마다 새로운 객체 생성을 막기위한 static 상수
    private static final ItemReadOneResponseDto EXISTING_ITEM_READ_ONE_DTO = new ItemReadOneResponseDto();
    private String title;
    private String description;
    private Integer minPriceWanted;
    private String status;

    public static ItemReadOneResponseDto entityToDto(SalesItemEntity entity) {
        ItemReadOneResponseDto dto = getItemReadOneDto();

        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setMinPriceWanted(entity.getMinPriceWanted());
        dto.setStatus(entity.getStatus());

        return dto;
    }

    private static ItemReadOneResponseDto getItemReadOneDto() {
        return EXISTING_ITEM_READ_ONE_DTO;
    }
}
