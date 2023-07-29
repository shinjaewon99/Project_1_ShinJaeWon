package com.example.mini_project.service.negotiation;

import com.example.mini_project.dto.ResponseDto;
import com.example.mini_project.dto.negotiation.request.NegotiationCreateRequestDto;
import com.example.mini_project.dto.negotiation.request.NegotiationDeleteRequestDto;
import com.example.mini_project.dto.negotiation.request.NegotiationUpdateRequestDto;
import com.example.mini_project.dto.negotiation.response.NegotiationPageResponseDto;
import com.example.mini_project.entity.NegotiationEntity;
import com.example.mini_project.entity.SalesItemEntity;
import com.example.mini_project.entity.UserEntity;
import com.example.mini_project.exception.NegotiationException;
import com.example.mini_project.repository.NegotiationRepository;
import com.example.mini_project.repository.SalesItemRepository;
import com.example.mini_project.repository.UserRepository;
import com.example.mini_project.util.item.ItemStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.mini_project.constant.CommonMessage.PAGE_SIZE_INTEGER_NUMBER;
import static com.example.mini_project.constant.negotiation.NegotiationMessage.*;
import static com.example.mini_project.constant.salesItem.SalesItemMessage.NOT_FIND_PRODUCT_MESSAGE;
import static com.example.mini_project.constant.user.UserMessage.NOT_FIND_USER_MESSAGE;

@Service
@RequiredArgsConstructor
public class NegotiationServiceImpl implements NegotiationService {
    private static int count = 0;
    private final SalesItemRepository salesItemRepository;
    private final NegotiationRepository negotiationRepository;
    private final UserRepository userRepository;
    NegotiationException negotiationException = new NegotiationException(); // 예외 핸들링

    // 생성 메소드
    @Override
    public NegotiationCreateRequestDto create(Long userId, Long itemId, NegotiationCreateRequestDto dto) {
        validateExistUserId(userId); // user 존재 여부 검증
        SalesItemEntity findItem = validateExistItemId(itemId);// 물품 존재 여부 검증
        negotiationException.validateCreateException(dto); // 구매 제안 생성 검증
        count++; // 구매 제안 생성 증가
        NegotiationEntity negotiationEntity = NegotiationEntity.builder()
                .itemId(findItem)
                .writer(dto.getWriter())
                .password(dto.getPassword())
                .suggestedPrice(dto.getSuggestedPrice())
                .status(String.valueOf(ItemStatus.제안))
                .build();

        return NegotiationCreateRequestDto.entityToDto(negotiationRepository.save(negotiationEntity));
    }

    // 페이징 조회 메소드
    @Override
    public Page<NegotiationPageResponseDto> readPage(Long userId, Long itemId, String writer, String password, Integer page) {
        validateExistUserId(userId); // user 존재 여부 검증
        SalesItemEntity salesItemEntity = validateExistItemId(itemId);// 물품 존재 여부 검증
        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE_INTEGER_NUMBER); // 페이징 처리는 0부터 시작

        // 물품을 올린 주인 ? / 구매제안을 등록한 사용자 ?
        // 1. 물품을 올린 주인 이라면 모든 구매제안 확인
        if (salesItemEntity.getWriter().equals(writer)
                && salesItemEntity.getPassword().equals(password)) {
            Page<NegotiationEntity> itemPage
                    = negotiationRepository.findAll(pageable);
            return itemPage.map(NegotiationPageResponseDto::entityToDto);
        }
        // 2. 구매제안을 등록한 사용자
        else {
            Page<NegotiationEntity> negoPage
                    = negotiationRepository.
                    findAllByWriterLikeAndPasswordLike(writer, password, pageable);
            return negoPage.map(NegotiationPageResponseDto::entityToDto);
        }
    }

    // 수정 메소드
    // 상태 변경 메소드
    @Override
    public void updateProposal(Long userId, Long itemId, Long proposalId,
                               NegotiationUpdateRequestDto dto, ResponseDto response) {
        validateExistUserId(userId); // user 존재 여부 검증
        SalesItemEntity salesItemEntity = validateExistItemId(itemId); // 물품 존재 여부 검증
        NegotiationEntity negotiationEntity = validateExistProposalId(proposalId); // 구매 제안 존재 여부 검증

        // 접근하는 사람이 대상 물품의 주인 인지? / 물품 구매자 인지?
        // 1.등록된 대상 물품의 주인
        if (salesItemEntity.getWriter().equals(dto.getWriter())
                && salesItemEntity.getPassword().equals(dto.getPassword())) {

            negotiationEntity.updateStatus(dto.getStatus());
            negotiationRepository.save(negotiationEntity);
            response.setMessage(STATUS_ALTER_MESSAGE);
        }

        // 2.기존의 구매 제안이 있는경우
        else {
            // 구매 제안에 등록된 작성자 혹은 비밀번호가 다른경우
            negotiationException.validateUpdateWriterOrPassword(negotiationEntity, dto);

            // 구매 제안에 저장된 상태가 수락이면서 입력 데이터 상태가 확정 인경우
            if (negotiationEntity.getStatus().equals(ACCEPT_MESSAGE)
                    && dto.getStatus().equals(CONFIRM_MESSAGE)) {
                negotiationEntity.updateStatus(dto.getStatus()); // 구매 제안 확정 상태 처리
                salesItemEntity.updateStatus(SOLD_OUT_MESSAGE); // 물품 판매 완료 처리
                response.setMessage(ACCEPT_BUYING_MESSAGE);
                count--;
            }
            // 구매 제안에 저장된 상태가 제안일 경우 단순한 수정
            else {
                // 구매 제안에 등록된 작성자 혹은 비밀번호가 다른경우
                negotiationException.validateUpdateWriterOrPassword(negotiationEntity, dto);

                negotiationEntity.updateProposal(dto.getWriter(), dto.getPassword(), dto.getSuggestedPrice());
                response.setMessage(UPDATE_MESSAGE);
            }

            List<NegotiationEntity> negoList = negotiationRepository.findAll();
            // 구매 제안이 확정이거나 제안이 아닌경우 다른 구매 제안은 모두 거절 처리
            if (!negotiationEntity.getStatus().equals(ACCEPT_MESSAGE) ||
                    !negotiationEntity.getStatus().equals(STATUS_MESSAGE)) {
                for (int i = count; i < negoList.size(); i++) {
                    negoList.get(i).updateStatus(REFUSE_MESSAGE);
                    negotiationRepository.save(negoList.get(i));
                }
            }
        }
        NegotiationUpdateRequestDto.entityToDto(negotiationRepository.save(negotiationEntity));
    }

    // 삭제 메소드
    @Override
    public void delete(Long userId, Long itemId, Long proposalId, NegotiationDeleteRequestDto dto) {
        validateExistUserId(userId); // user 존재 여부 검증
        validateExistItemId(itemId); // 물품 존재 여부 검증
        NegotiationEntity entity = validateExistProposalId(proposalId); // 구매 제안 존재 여부 검증

        negotiationException.validateDeleteWriterOrPassword(entity, dto);
        negotiationRepository.delete(entity);
    }

    // itemId 존재 여부 검증 메소드
    @Override
    public SalesItemEntity validateExistItemId(Long itemId) {
        return salesItemRepository
                .findById(itemId).orElseThrow(() -> new IllegalArgumentException(NOT_FIND_PRODUCT_MESSAGE));
    }

    // proposalId 존재 여부 검증 메소드
    @Override
    public NegotiationEntity validateExistProposalId(Long proposalId) {
        return negotiationRepository
                .findById(proposalId).orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_PROPOSAL_MESSAGE));
    }

    // userId 존재 여부 검증 메소드
    @Override
    @Transactional
    public UserEntity validateExistUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FIND_USER_MESSAGE));
    }
}