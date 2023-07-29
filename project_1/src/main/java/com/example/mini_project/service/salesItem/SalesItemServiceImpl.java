package com.example.mini_project.service.salesItem;

import com.example.mini_project.dto.salesItem.request.ItemCreateRequestDto;
import com.example.mini_project.dto.salesItem.request.ItemDeleteRequestDto;
import com.example.mini_project.dto.salesItem.request.ItemUpdateRequestDto;
import com.example.mini_project.dto.salesItem.response.ItemPageResponseDto;
import com.example.mini_project.dto.salesItem.response.ItemReadOneResponseDto;
import com.example.mini_project.entity.SalesItemEntity;
import com.example.mini_project.exception.SalesItemException;
import com.example.mini_project.repository.SalesItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.example.mini_project.constant.salesItem.SalesItemMessage.*;

@Service
@RequiredArgsConstructor
public class SalesItemServiceImpl implements SalesItemService {
    private final SalesItemRepository repository;
    SalesItemException salesItemException = new SalesItemException(); // 예외 핸들링 클래스

    // 생성 메소드
    @Override
    public ItemCreateRequestDto create(ItemCreateRequestDto dto) {
        salesItemException.validateCreateException(dto); // 제목, 설명, 최소 가격, 작성자, 비밀번호 예외 검증

        SalesItemEntity entity = SalesItemEntity
                .builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .minPriceWanted(dto.getMinPriceWanted())
                .saleMessage(SALE_MESSAGE)
                .writer(dto.getWriter())
                .password(dto.getPassword())
                .build();

        return ItemCreateRequestDto.entityToDto(repository.save(entity));
    }

    // 전체 조회 메소드
    @Override
    public List<ItemCreateRequestDto> readAll() {
        List<ItemCreateRequestDto> itemCreateRequestDtoList = new ArrayList<>();
        for (SalesItemEntity entity : repository.findAll()) {
            itemCreateRequestDtoList.add(ItemCreateRequestDto.entityToDto(entity));
        }

        return itemCreateRequestDtoList;
    }

    // 단일 조회 메소드
    @Override
    public ItemReadOneResponseDto readOne(Long id) {
        SalesItemEntity entity = validateExistItemId(id); // 물품 존재 여부 검증

        return ItemReadOneResponseDto.entityToDto(entity);
    }

    // 페이지 조회 메소드
    @Override
    public Page<ItemPageResponseDto> readPage(Integer page, Integer limit) {
        // PageRequest.of(page,size,Sort)
        // page : 0부터 시작, size : 페이징 갯수, Sort : 오름차순, 내림차순
        Pageable pageable = PageRequest.of(page, limit, Sort.by("id").ascending());

        Page<SalesItemEntity> itemPage = repository.findAll(pageable);

        // 엔티티를 그대로 반환하면 원치않은 정보가 반환될수 있음으로, PageDto로 변환후 반환
        return itemPage.map(ItemPageResponseDto::pageResponse);
    }

    // 수정 메소드
    @Override
    public void update(Long id, ItemUpdateRequestDto dto) {
        SalesItemEntity entity = validateExistItemId(id); // 물품 존재 여부 검증
        salesItemException.validateWriterOrPassword(entity, dto); // 등록된 물품에 대한 작성자 혹은 비밀번호 검증

        entity.updateItem(dto.getTitle(), dto.getDescription(),
                dto.getMinPriceWanted(), dto.getWriter(), dto.getPassword());

        repository.save(entity);

        ItemUpdateRequestDto.entityToDto(entity);
    }

    // 파일 업로드 메소드
    @Override
    public void uploadFile(Long itemId, MultipartFile file, ItemCreateRequestDto dto) {
        SalesItemEntity entity = validateExistItemId(itemId); // 물품 존재 여부 검증
        salesItemException.validateWriterOrPassword(entity, dto); // 등록된 물품에 대한 작성자 혹은 비밀번호 검증
        salesItemException.validateExistFileException(file); // 업로드 파일 존재 여부 검증

        String fileLocation = String.format("images/%d", itemId);
        String fileName = file.getOriginalFilename();
        String filePath = fileLocation + fileName; // 파일 경로 할당

        try {
            Files.createDirectories(Path.of(fileLocation)); // 파일 업로드 위치
        } catch (IOException exception) {
            throw new IllegalArgumentException(NOT_UPLOAD_FILE_MESSAGE);
        }

        try {
            file.transferTo(Path.of(filePath)); // 파일 업로드 처리
        } catch (IOException exception) {
            throw new IllegalArgumentException(NOT_CREATE_FOLDER_MESSAGE);
        }

        entity.uploadFile(filePath); // setter 대신 메소드 처리
        repository.save(entity);
    }

    // 삭제 메소드
    @Override
    public void delete(Long id, ItemDeleteRequestDto dto) {
        SalesItemEntity entity = validateExistItemId(id); // 물품 존재 여부 검증
        salesItemException.validateWriterOrPassword(entity, dto); // 등록된 물품에 대한 작성자 혹은 비밀번호 검증

        repository.delete(entity);
    }

    // itemId 존재 여부 검증 메소드
    @Override
    public SalesItemEntity validateExistItemId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FIND_PRODUCT_MESSAGE));
    }
}