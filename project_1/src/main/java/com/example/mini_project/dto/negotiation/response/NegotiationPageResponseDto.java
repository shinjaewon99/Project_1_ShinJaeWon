package com.example.mini_project.dto.negotiation.response;

import com.example.mini_project.entity.NegotiationEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NegotiationPageResponseDto {
    private Long id;
    private Integer suggestedPrice;
    private String status;

    public static NegotiationPageResponseDto entityToDto(NegotiationEntity entity) {
        NegotiationPageResponseDto dto = new NegotiationPageResponseDto();

        dto.setId(entity.getId());
        dto.setSuggestedPrice(entity.getSuggestedPrice());
        dto.setStatus(entity.getStatus());

        return dto;
    }
}
