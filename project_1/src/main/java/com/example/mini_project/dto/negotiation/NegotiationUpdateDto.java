package com.example.mini_project.dto.negotiation;

import com.example.mini_project.entity.NegotiationEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NegotiationUpdateDto {
    private String writer;
    private String password;
    private Integer suggestedPrice;
    private String status;

    public static NegotiationUpdateDto entityToDto(NegotiationEntity entity) {
        NegotiationUpdateDto dto = new NegotiationUpdateDto();

        dto.setWriter(entity.getWriter());
        dto.setPassword(entity.getPassword());
        dto.setSuggestedPrice(entity.getSuggestedPrice());
        dto.setStatus(entity.getStatus());

        return dto;
    }
}
