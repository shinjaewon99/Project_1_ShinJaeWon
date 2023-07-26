package com.example.mini_project.dto.salesItem;

import com.example.mini_project.entity.SalesItemEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesItemReadOneDto {
    private String title;
    private String description;
    private Integer minPriceWanted;
    private String status;

    public static SalesItemReadOneDto entityToDto(SalesItemEntity entity) {
        SalesItemReadOneDto dto = new SalesItemReadOneDto();

        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setMinPriceWanted(entity.getMinPriceWanted());
        dto.setStatus(entity.getStatus());

        return dto;
    }
}
