package com.example.mini_project.dto.negotiation.request;

import com.example.mini_project.dto.negotiation.request.base.NegotiationRequest;
import com.example.mini_project.entity.NegotiationEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NegotiationCreateRequestDto extends NegotiationRequest {
    // 호출 할때마다 새로운 객체 생성을 막기위한 static 상수
    private static final NegotiationCreateRequestDto EXISTING_NEGOTIATION_CRETE_DTO = new NegotiationCreateRequestDto();
    private Integer suggestedPrice;

    public static NegotiationCreateRequestDto entityToDto(NegotiationEntity negotiationEntity) {
        NegotiationCreateRequestDto dto = getNegotiationCreateDto();

        dto.setWriter(negotiationEntity.getWriter());
        dto.setPassword(negotiationEntity.getPassword());
        dto.setSuggestedPrice(negotiationEntity.getSuggestedPrice());

        return dto;
    }

    private static NegotiationCreateRequestDto getNegotiationCreateDto() {
        return EXISTING_NEGOTIATION_CRETE_DTO;
    }
}
