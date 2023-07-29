package com.example.mini_project.service.negotiation;

import com.example.mini_project.dto.ResponseDto;
import com.example.mini_project.dto.negotiation.request.NegotiationCreateRequestDto;
import com.example.mini_project.dto.negotiation.request.NegotiationDeleteRequestDto;
import com.example.mini_project.dto.negotiation.request.NegotiationUpdateRequestDto;
import com.example.mini_project.dto.negotiation.response.NegotiationPageResponseDto;
import com.example.mini_project.entity.NegotiationEntity;
import com.example.mini_project.entity.SalesItemEntity;
import com.example.mini_project.entity.UserEntity;
import org.springframework.data.domain.Page;

public interface NegotiationService {
    NegotiationCreateRequestDto create(Long userId, Long itemId, NegotiationCreateRequestDto dto);

    void updateProposal(Long userId, Long itemId, Long proposalId, NegotiationUpdateRequestDto dto, ResponseDto response);

    Page<NegotiationPageResponseDto> readPage(Long userId, Long itemId, String writer, String password, Integer page);

    void delete(Long userId, Long itemId, Long proposalId, NegotiationDeleteRequestDto dto);

    SalesItemEntity validateExistItemId(Long itemId);

    NegotiationEntity validateExistProposalId(Long proposalId);

    UserEntity validateExistUserId(Long userId);
}
