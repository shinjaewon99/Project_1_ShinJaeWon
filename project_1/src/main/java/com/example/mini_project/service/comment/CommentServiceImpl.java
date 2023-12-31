package com.example.mini_project.service.comment;

import com.example.mini_project.dto.comment.request.CommentCreateRequestDto;
import com.example.mini_project.dto.comment.request.CommentDeleteRequestDto;
import com.example.mini_project.dto.comment.request.CommentReplyRequestDto;
import com.example.mini_project.dto.comment.request.CommentUpdateRequestDto;
import com.example.mini_project.dto.comment.response.CommentPageResponseDto;
import com.example.mini_project.entity.CommentEntity;
import com.example.mini_project.entity.SalesItemEntity;
import com.example.mini_project.entity.UserEntity;
import com.example.mini_project.exception.CommentException;
import com.example.mini_project.repository.CommentRepository;
import com.example.mini_project.repository.SalesItemRepository;
import com.example.mini_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.mini_project.constant.CommonMessage.PAGE_SIZE_INTEGER_NUMBER;
import static com.example.mini_project.constant.CommonMessage.PAGE_START_INTEGER_NUMBER;
import static com.example.mini_project.constant.comment.CommentMessage.NOT_FIND_COMMENT_MESSAGE;
import static com.example.mini_project.constant.salesItem.SalesItemMessage.NOT_FIND_PRODUCT_MESSAGE;
import static com.example.mini_project.constant.user.UserMessage.NOT_FIND_USER_MESSAGE;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentServiceImpl implements CommentService, CommonCommentService {
    private final CommentRepository commentRepository;
    private final SalesItemRepository salesItemRepository;
    private final UserRepository userRepository;
    CommentException commentException = new CommentException();

    // 생성 메소드
    @Override
    public CommentCreateRequestDto create(Long userId, Long itemId, CommentCreateRequestDto dto) {
        validateExistUserId(userId); // user 존재 여부 검증
        SalesItemEntity findItem = validateExistItemId(itemId); // 물품 id가 존재하지 않은경우
        commentException.validateCreateException(dto); // 댓글 생성 검증

        CommentEntity entity = CommentEntity
                .builder()
                .itemId(findItem)
                .writer(dto.getWriter())
                .password(dto.getPassword())
                .content(dto.getContent())
                .build();

        return CommentCreateRequestDto.entityToDto(commentRepository.save(entity));
    }

    // 페이지 조회 메소드
    @Override
    @Transactional(readOnly = true)
    public Page<CommentPageResponseDto> readPage(Long userId, Long itemId) {
        validateExistUserId(userId);
        validateExistItemId(itemId); // 물품 존재 여부 검증

        Pageable pageable = PageRequest.of(PAGE_START_INTEGER_NUMBER, PAGE_SIZE_INTEGER_NUMBER);
        Page<CommentEntity> commentsPage = commentRepository.findAll(pageable);

        // commentsPage의 엔티티 반환을 DTO 클래스로 변환하여 반환
        return commentsPage.map(CommentPageResponseDto::pageResponse);
    }

    // 수정 메소드
    @Override
    public void update(Long userId, Long itemId, Long commentId, CommentUpdateRequestDto dto) {
        validateExistUserId(userId);
        validateExistItemId(itemId); // 물품 존재 여부 검증
        CommentEntity entity = validateExistCommentId(commentId); // 댓글 존재 여부 검증
        commentException.validateWriterOrPassword(entity, dto); // 등록된 댓글의 작성자 혹은 비밀번호 검증
        entity.updateComment(dto.getWriter(), dto.getPassword(), dto.getContent());

        commentRepository.save(entity);
        CommentCreateRequestDto.entityToDto(entity);
    }

    // 작성한 댓글에 대한 답글 메소드
    @Override
    public void commentReply(Long userId, Long itemId, Long commentId, CommentReplyRequestDto dto) {
        validateExistUserId(userId);
        SalesItemEntity findItem = validateExistItemId(itemId); // 물품 존재 여부 검증
        CommentEntity entity = validateExistCommentId(commentId); // 댓글 존재 여부 검증
        commentException.validateWriterOrPasswordFromSalesItem(findItem, dto); // 등록된 물품에 대한 작성자 혹은 비밀번호 검증 메소드
        entity.commentReplyCreate(dto.getReply());

        commentRepository.save(entity);
        CommentCreateRequestDto.entityToDto(entity);
    }

    // 삭제 메소드
    @Override
    public void delete(Long userId, Long itemId, Long commentId, CommentDeleteRequestDto dto) {
        validateExistUserId(userId);
        validateExistItemId(itemId); // 물품 존재 여부 검증
        CommentEntity entity = validateExistCommentId(commentId); // 댓글 존재 여부 검증
        commentException.validateWriterOrPassword(entity, dto); // 등록된 댓글의 작성자 혹은 비밀번호가 다를경우
        commentRepository.delete(entity);
    }

    // itemId 존재 여부 검증 메소드
    @Override
    public SalesItemEntity validateExistItemId(Long itemId) {
        return salesItemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FIND_PRODUCT_MESSAGE));
    }

    // commentId 존재 여부 검증 메소드
    @Override
    public CommentEntity validateExistCommentId(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FIND_COMMENT_MESSAGE));
    }

    // userId 존재 여부 검증 메소드
    @Override
    public UserEntity validateExistUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FIND_USER_MESSAGE));
    }

    // 인증되지 않은 회원도 댓글을 조회하여 열람할수 있다.
    @Override
    @Transactional(readOnly = true)
    public Page<CommentPageResponseDto> readPage(Long itemId) {
        validateExistItemId(itemId); // 물품 존재 여부 검증

        Pageable pageable = PageRequest.of(PAGE_START_INTEGER_NUMBER, PAGE_SIZE_INTEGER_NUMBER);
        Page<CommentEntity> commentsPage = commentRepository.findAll(pageable);

        // commentsPage의 엔티티 반환을 DTO 클래스로 변환하여 반환
        return commentsPage.map(CommentPageResponseDto::pageResponse);
    }
}
