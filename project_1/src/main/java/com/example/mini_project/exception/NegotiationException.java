package com.example.mini_project.exception;

import com.example.mini_project.dto.negotiation.request.NegotiationCreateRequestDto;
import com.example.mini_project.dto.negotiation.request.NegotiationDeleteRequestDto;
import com.example.mini_project.dto.negotiation.request.NegotiationUpdateRequestDto;
import com.example.mini_project.entity.NegotiationEntity;

import static com.example.mini_project.constant.negotiation.NegotiationMessage.NOT_SAME_WRITER_PASSWORD_MESSAGE;
import static com.example.mini_project.constant.negotiation.NegotiationMessage.VALIDATE_CREATE_MESSAGE;


public class NegotiationException {

    // 구매제안 생성 검증 메소드
    public void validateCreateException(NegotiationCreateRequestDto dto) {
        if (dto.getWriter() == null || dto.getPassword() == null
                || dto.getSuggestedPrice() == null) {
            throw new IllegalArgumentException(VALIDATE_CREATE_MESSAGE);
        }
    }

    // 구매제안의 작성자 혹은 비밀번호 검증 메소드
    public void validateUpdateWriterOrPassword(NegotiationEntity entity, NegotiationUpdateRequestDto dto) {
        if (!entity.getWriter().equals(dto.getWriter()) || !entity.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException(NOT_SAME_WRITER_PASSWORD_MESSAGE);
        }
    }

    // 구매제안의 작성자 혹은 비밀번호 검증 메소드
    public void validateDeleteWriterOrPassword(NegotiationEntity entity, NegotiationDeleteRequestDto dto) {
        if (!entity.getWriter().equals(dto.getWriter()) || !entity.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException(NOT_SAME_WRITER_PASSWORD_MESSAGE);
        }
    }
}
