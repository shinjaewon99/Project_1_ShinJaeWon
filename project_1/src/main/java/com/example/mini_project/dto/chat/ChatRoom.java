package com.example.mini_project.dto.chat;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ChatRoom {

    private String roomId;
    private String roomName;

    // 채팅방 생성 메소드
    public static ChatRoom createRoom(String roomName) {
        ChatRoom room = new ChatRoom();
        room.roomId = UUID.randomUUID().toString(); // 채팅방의 고유 식별자로 UUID를 사용
        room.roomName = roomName;

        return room;
    }
}
