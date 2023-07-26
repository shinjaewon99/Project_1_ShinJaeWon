package com.example.mini_project.dto.salesItem;

import com.example.mini_project.entity.SalesItemEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesItemCreateDto {

    private String title;
    private String description;
    private Integer minPriceWanted;
    private String status;
    private String writer;
    private String password;


    public static SalesItemCreateDto entityToDto(SalesItemEntity entity) {

        SalesItemCreateDto dto = new SalesItemCreateDto();

        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setMinPriceWanted(entity.getMinPriceWanted());
        dto.setStatus(entity.getStatus());
        dto.setWriter(entity.getWriter());
        dto.setPassword(dto.getPassword());

        return dto;
    }
}
