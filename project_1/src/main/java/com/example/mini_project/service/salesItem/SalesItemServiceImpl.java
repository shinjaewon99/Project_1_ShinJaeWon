package com.example.mini_project.service.salesItem;

import com.example.mini_project.dto.salesItem.request.ItemCreateRequestDto;
import com.example.mini_project.dto.salesItem.request.ItemDeleteRequestDto;
import com.example.mini_project.dto.salesItem.request.ItemUpdateRequestDto;
import com.example.mini_project.dto.salesItem.response.ItemPageResponseDto;
import com.example.mini_project.dto.salesItem.response.ItemReadOneResponseDto;
import com.example.mini_project.entity.SalesItemEntity;
import com.example.mini_project.entity.UserEntity;
import com.example.mini_project.exception.SalesItemException;
import com.example.mini_project.repository.SalesItemRepository;
import com.example.mini_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.example.mini_project.constant.salesItem.SalesItemMessage.*;
import static com.example.mini_project.constant.user.UserMessage.NOT_FIND_USER_MESSAGE;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SalesItemServiceImpl implements SalesItemService, CommonSalesItemService {
    private final SalesItemRepository salesItemRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    SalesItemException salesItemException = new SalesItemException(); // 예외 핸들링 클래스

    // 생성 메소드
    // create 메소드에만 등록된 회원과의 검증 메소드가 존재하는데 이유로는 수정, 삭제 메소드 등에서는 물품이 존재하는지 검증합니다.
    // 즉 create 메소드를 거쳐 등록된 회원인것이 증명되어 물품이 등록되게 된다면 수정, 삭제 메소드에서는 등록된 회원과의 검증이 필요없습니다.
    // 추가로 comment, negotiation 클래스에서도 물품이 등록되었는지 검증을 하기 때문에 회원을 검증할 필요가 없게 됩니다.
    @Override
    @Transactional
    public ItemCreateRequestDto create(Long userId, ItemCreateRequestDto dto) {
        UserEntity findUser = validateExistUserId(userId); // user 존재 여부 검증
        salesItemException.validateCreateException(dto); // 제목, 설명, 최소 가격, 작성자, 비밀번호 예외 검증

        // 등록된 회원과 상품을 등록하려고 하는 회원의 이름과 비밀번호가 같은지 검증
        if (!isValidExistUser(findUser, dto.getWriter(), dto.getPassword())) {
            throw new IllegalArgumentException("등록된 회원의 정보와 작성자의 정보가 다릅니다.");
        }

        SalesItemEntity entity = SalesItemEntity
                .builder()
                .userId(findUser)
                .title(dto.getTitle())
                .description(dto.getDescription())
                .minPriceWanted(dto.getMinPriceWanted())
                .saleMessage(SALE_MESSAGE)
                .writer(dto.getWriter())
                .password(dto.getPassword())
                .build();

        return ItemCreateRequestDto.entityToDto(salesItemRepository.save(entity));
    }

    // 전체 조회 메소드
    @Override
    public List<ItemCreateRequestDto> readAll() {
        List<ItemCreateRequestDto> itemCreateRequestDtoList = new ArrayList<>();
        for (SalesItemEntity entity : salesItemRepository.findAll()) {
            itemCreateRequestDtoList.add(ItemCreateRequestDto.entityToDto(entity));
        }

        return itemCreateRequestDtoList;
    }

    // 단일 조회 메소드
    @Override
    public ItemReadOneResponseDto readOne(Long userId, Long itemId) {
        validateExistUserId(userId); // user 존재 여부 검증
        SalesItemEntity entity = validateExistItemId(itemId); // 물품 존재 여부 검증

        return ItemReadOneResponseDto.entityToDto(entity);
    }

    // 페이지 조회 메소드
    @Override
    public Page<ItemPageResponseDto> readPage(Long userId, Integer page, Integer limit) {
        validateExistUserId(userId); // user 존재 여부 검증
        // PageRequest.of(page,size,Sort)
        // page : 0부터 시작, size : 페이징 갯수, Sort : 오름차순, 내림차순
        Pageable pageable = PageRequest.of(page, limit, Sort.by("id").ascending());

        Page<SalesItemEntity> itemPage = salesItemRepository.findAll(pageable);

        // 엔티티를 그대로 반환하면 원치않은 정보가 반환될수 있음으로, PageDto로 변환후 반환
        return itemPage.map(ItemPageResponseDto::pageResponse);
    }

    // 수정 메소드
    @Override
    @Transactional
    public void update(Long userId, Long itemId, ItemUpdateRequestDto dto) {
        validateExistUserId(userId); // user 존재 여부 검증
        SalesItemEntity entity = validateExistItemId(itemId); // 물품 존재 여부 검증
        salesItemException.validateWriterOrPassword(entity, dto); // 등록된 물품에 대한 작성자 혹은 비밀번호 검증

        entity.updateItem(dto.getTitle(), dto.getDescription(),
                dto.getMinPriceWanted(), dto.getWriter(), dto.getPassword());

        salesItemRepository.save(entity);

        ItemUpdateRequestDto.entityToDto(entity);
    }

    // 파일 업로드 메소드
    @Override
    @Transactional
    public void uploadFile(Long userId, Long itemId, MultipartFile file, ItemCreateRequestDto dto) {
        validateExistUserId(userId); // user 존재 여부 검증
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
        salesItemRepository.save(entity);
    }

    // 삭제 메소드
    @Override
    @Transactional
    public void delete(Long userId, Long id, ItemDeleteRequestDto dto) {
        validateExistUserId(userId); // user 존재 여부 검증
        SalesItemEntity entity = validateExistItemId(id); // 물품 존재 여부 검증
        salesItemException.validateWriterOrPassword(entity, dto); // 등록된 물품에 대한 작성자 혹은 비밀번호 검증

        salesItemRepository.delete(entity);
    }

    // itemId 존재 여부 검증 메소드
    @Override
    @Transactional
    public SalesItemEntity validateExistItemId(Long id) {
        return salesItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FIND_PRODUCT_MESSAGE));
    }

    @Override
    @Transactional
    public UserEntity validateExistUserId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FIND_USER_MESSAGE));
    }

    // 등록되지 않은 회원도 조회는 할수 있다.
    @Override
    public ItemReadOneResponseDto readOne(Long itemId) {
        SalesItemEntity findItem = validateExistItemId(itemId);
        return ItemReadOneResponseDto.entityToDto(findItem);
    }

    @Override
    public Page<ItemPageResponseDto> readPage(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by("id").ascending());

        Page<SalesItemEntity> itemPage = salesItemRepository.findAll(pageable);

        return itemPage.map(ItemPageResponseDto::pageResponse);
    }

    // 등록된 회원과 물품을 등록하려고 하는 사용자 검증 메소드
    private boolean isValidExistUser(UserEntity entity, String writer, String password) {
        return entity.getUserId().equals(writer) &&
                passwordEncoder.matches(password, entity.getPassword()); // .matches(입력받은 비교할 비밀번호, 이미 암호화된 비밀번호)
    }
}