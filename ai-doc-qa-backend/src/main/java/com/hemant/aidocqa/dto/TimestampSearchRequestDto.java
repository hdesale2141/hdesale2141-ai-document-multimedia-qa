package com.hemant.aidocqa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimestampSearchRequestDto {

    private Long fileId;

    private String topic;
}