package com.hemant.aidocqa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TimestampResponseDto {

    private Double startTime;

    private Double endTime;

    private String text;
}