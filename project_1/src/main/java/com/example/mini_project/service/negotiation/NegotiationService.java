package com.example.mini_project.service.negotiation;

import com.example.mini_project.dto.ResponseDto;
import com.example.mini_project.dto.negotiation.NegotiationCreateDto;
import com.example.mini_project.dto.negotiation.NegotiationDeleteDto;
import com.example.mini_project.dto.negotiation.NegotiationPageResponseDto;
import com.example.mini_project.dto.negotiation.NegotiationUpdateDto;
import com.example.mini_project.entity.NegotiationEntity;
import com.example.mini_project.entity.SalesItemEntity;
import org.springframework.data.domain.Page;

public interface NegotiationService {
    NegotiationCreateDto create(Long itemId, NegotiationCreateDto dto);

    void updateProposal(Long itemId, Long proposalId, NegotiationUpdateDto dto, ResponseDto response);

    Page<NegotiationPageResponseDto> readPage(Long itemId, String writer, String password, Integer page);

    void delete(Long itemId, Long proposalId, NegotiationDeleteDto dto);

    SalesItemEntity validateExistItemId(Long itemId);

    NegotiationEntity validateExistProposalId(Long proposalId);
}
