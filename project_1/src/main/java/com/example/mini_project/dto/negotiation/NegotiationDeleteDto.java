package com.example.mini_project.dto.negotiation;

import com.example.mini_project.entity.NegotiationEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NegotiationDeleteDto {
    private String writer;
    private String password;

    public static NegotiationDeleteDto entityToDto(NegotiationEntity entity) {
        NegotiationDeleteDto dto = new NegotiationDeleteDto();

        dto.setWriter(entity.getWriter());
        dto.setPassword(entity.getPassword());

        return dto;
    }
}
