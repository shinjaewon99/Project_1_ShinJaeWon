package com.example.mini_project.dto.negotiation.request;

import com.example.mini_project.dto.negotiation.request.base.NegotiationRequest;
import com.example.mini_project.entity.NegotiationEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NegotiationUpdateRequestDto extends NegotiationRequest {
    // 호출 할때마다 새로운 객체 생성을 막기위한 static 상수
    private static final NegotiationUpdateRequestDto EXISTING_NEGOTIATION_UPDATE_DTO = new NegotiationUpdateRequestDto();
    private Integer suggestedPrice;
    private String status;

    public static NegotiationUpdateRequestDto entityToDto(NegotiationEntity entity) {
        NegotiationUpdateRequestDto dto = getNegotiationUpdateDto();

        dto.setWriter(entity.getWriter());
        dto.setPassword(entity.getPassword());
        dto.setSuggestedPrice(entity.getSuggestedPrice());
        dto.setStatus(entity.getStatus());

        return dto;
    }

    private static NegotiationUpdateRequestDto getNegotiationUpdateDto() {
        return EXISTING_NEGOTIATION_UPDATE_DTO;
    }
}
