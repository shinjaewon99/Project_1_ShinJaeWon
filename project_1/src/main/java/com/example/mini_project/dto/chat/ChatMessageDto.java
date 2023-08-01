package com.example.mini_project.dto.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDto {

    private Long roomId;
    private String sender; // 보내는 사람
    private String message; // 메시지 내용
}
