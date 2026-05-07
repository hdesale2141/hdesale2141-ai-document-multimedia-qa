package com.hemant.aidocqa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRequestDto {

    private Long fileId;

    private String question;
}