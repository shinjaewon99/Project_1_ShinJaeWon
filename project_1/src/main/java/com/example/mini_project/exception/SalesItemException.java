package com.example.mini_project.exception;

import com.example.mini_project.dto.salesItem.SalesItemCreateDto;
import com.example.mini_project.entity.SalesItemEntity;
import org.springframework.web.multipart.MultipartFile;

import static com.example.mini_project.constant.salesItem.SalesItemMessage.*;

public class SalesItemException {

    // 물품 생성 검증 메소드
    public void validateCreateException(SalesItemCreateDto dto) {
        if (dto.getTitle() == null || dto.getDescription() == null ||
                dto.getMinPriceWanted() == null || dto.getWriter() == null ||
                dto.getPassword() == null) {
            throw new IllegalArgumentException(VALIDATE_CREATE_MESSAGE);
        }
    }

    // 등록된 물품에 대한 작성자 혹은 비밀번호 검증
    public void validateWriterOrPassword(SalesItemEntity entity, SalesItemCreateDto dto) {
        if (!entity.getPassword().equals(dto.getPassword()) || !entity.getWriter().equals(dto.getWriter())) {
            throw new IllegalArgumentException(NOT_SAME_WRITER_PASSWORD_MESSAGE);
        }
    }

    // 업로드 파일 존재 여부 검증
    public void validateExistFileException(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException(NOT_EXIST_FILE_MESSAGE);
        }
    }
}
