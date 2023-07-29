package com.example.mini_project.dto.salesItem.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequest {
    private String title;
    private String description;
    private Integer minPriceWanted;
    private String status;
    private String writer;
    private String password;
}
