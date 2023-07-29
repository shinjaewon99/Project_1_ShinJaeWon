package com.example.mini_project.dto.comment.request.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
    private String content;
    private String writer;
    private String password;

}
