package com.example.mini_project.service.salesItem;

import com.example.mini_project.dto.salesItem.SalesItemCreateDto;
import com.example.mini_project.dto.salesItem.SalesItemPageResponseDto;
import com.example.mini_project.dto.salesItem.SalesItemReadOneDto;
import com.example.mini_project.entity.SalesItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

// Service 인터페이스와 구현 클래스를 분리함으로써 OCP (개방 폐쇄 원칙을 지켰다) = 확장에는 열려있어야하고, 수정에는 닫혀있어야된다.
// 단점 : 코드 구조가 복잡해지고, 분석하는 과정에서 오래걸린다.
public interface SalesItemService {
    SalesItemCreateDto create(SalesItemCreateDto dto);

    List<SalesItemCreateDto> readAll();

    SalesItemReadOneDto readOne(Long id);

    Page<SalesItemPageResponseDto> readPage(Integer page, Integer limit);

    void update(Long id, SalesItemCreateDto dto);

    void uploadFile(Long id, MultipartFile file, SalesItemCreateDto dto) throws IOException;

    void delete(Long id, SalesItemCreateDto dto);

    SalesItemEntity validateExistItemId(Long id);
}
