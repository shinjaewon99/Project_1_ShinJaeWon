package com.example.mini_project.dto.negotiation.request;

import com.example.mini_project.dto.negotiation.request.base.NegotiationRequest;
import com.example.mini_project.entity.NegotiationEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NegotiationDeleteRequestDto extends NegotiationRequest {
    // 호출 할때마다 새로운 객체 생성을 막기위한 static 상수
    private static final NegotiationDeleteRequestDto EXISTING_NEGOTIATION_DELETE_DTO = new NegotiationDeleteRequestDto();

    public static NegotiationDeleteRequestDto entityToDto(NegotiationEntity entity) {
        NegotiationDeleteRequestDto dto = getNegotiationDeleteDto();

        dto.setWriter(entity.getWriter());
        dto.setPassword(entity.getPassword());

        return dto;
    }

    private static NegotiationDeleteRequestDto getNegotiationDeleteDto() {
        return EXISTING_NEGOTIATION_DELETE_DTO;
    }
}
