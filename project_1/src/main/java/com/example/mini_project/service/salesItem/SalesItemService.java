package com.example.mini_project.service.salesItem;

import com.example.mini_project.dto.salesItem.request.ItemCreateRequestDto;
import com.example.mini_project.dto.salesItem.request.ItemDeleteRequestDto;
import com.example.mini_project.dto.salesItem.request.ItemUpdateRequestDto;
import com.example.mini_project.dto.salesItem.response.ItemPageResponseDto;
import com.example.mini_project.dto.salesItem.response.ItemReadOneResponseDto;
import com.example.mini_project.entity.SalesItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

// Service 인터페이스와 구현 클래스를 분리함으로써 OCP (개방 폐쇄 원칙을 지켰다) = 확장에는 열려있어야하고, 수정에는 닫혀있어야된다.
// 단점 : 코드 구조가 복잡해지고, 분석하는 과정에서 오래걸린다.
public interface SalesItemService {
    ItemCreateRequestDto create(ItemCreateRequestDto dto);

    List<ItemCreateRequestDto> readAll();

    ItemReadOneResponseDto readOne(Long id);

    Page<ItemPageResponseDto> readPage(Integer page, Integer limit);

    void update(Long id, ItemUpdateRequestDto dto);

    void uploadFile(Long id, MultipartFile file, ItemCreateRequestDto dto) throws IOException;

    void delete(Long id, ItemDeleteRequestDto dto);

    SalesItemEntity validateExistItemId(Long id);
}