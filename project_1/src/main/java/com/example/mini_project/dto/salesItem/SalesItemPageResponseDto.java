package com.example.mini_project.dto.salesItem;

import com.example.mini_project.dto.user.Request.RegisterRequest;
import com.example.mini_project.entity.SalesItemEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // @Getter를 작성하지 않으면, controller단에서 반환형인 Page<SalesItemPageResponseDto>를 반환하지못하여 406 에러를 뱉는다.
@Setter
@NoArgsConstructor
// 페이지 반환 DTO
public class SalesItemPageResponseDto {
    // 호출 할때마다 새로운 객체 생성을 막기위한 static 상수
    private static final SalesItemPageResponseDto EXISTING_ITEM_PAGE_DTO = new SalesItemPageResponseDto();
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Integer minPriceWanted;
    private String status;

    public static SalesItemPageResponseDto pageResponse(SalesItemEntity entity) {
        SalesItemPageResponseDto dto = getItemPageDto();

        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setImageUrl(entity.getImageUrl());
        dto.setMinPriceWanted(entity.getMinPriceWanted());
        dto.setStatus(entity.getStatus());

        return dto;
    }
    private static SalesItemPageResponseDto getItemPageDto() {
        return EXISTING_ITEM_PAGE_DTO;
    }
}
