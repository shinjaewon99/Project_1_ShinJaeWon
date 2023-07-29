package com.example.mini_project.dto.negotiation.response;

import com.example.mini_project.entity.NegotiationEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NegotiationPageResponseDto {
    // 호출 할때마다 새로운 객체 생성을 막기위한 static 상수
    private static final NegotiationPageResponseDto EXISTING_NEGOTIATION_PAGE_DTO = new NegotiationPageResponseDto();
    private Long id;
    private Integer suggestedPrice;
    private String status;

    public static NegotiationPageResponseDto entityToDto(NegotiationEntity entity) {
        NegotiationPageResponseDto dto = getNegotiationPageDto();

        dto.setId(entity.getId());
        dto.setSuggestedPrice(entity.getSuggestedPrice());
        dto.setStatus(entity.getStatus());

        return dto;
    }

    private static NegotiationPageResponseDto getNegotiationPageDto() {
        return EXISTING_NEGOTIATION_PAGE_DTO;
    }
}
