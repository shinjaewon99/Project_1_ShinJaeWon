package com.example.mini_project.dto.negotiation;

import com.example.mini_project.entity.NegotiationEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NegotiationCreateDto {
    private Integer suggestedPrice;
    private String status;
    private String writer;
    private String password;

    public static NegotiationCreateDto entityToDto(NegotiationEntity negotiationEntity) {
        NegotiationCreateDto dto = new NegotiationCreateDto();

        dto.setWriter(negotiationEntity.getWriter());
        dto.setPassword(negotiationEntity.getPassword());
        dto.setSuggestedPrice(negotiationEntity.getSuggestedPrice());
        dto.setStatus(negotiationEntity.getStatus());

        return dto;
    }

}
