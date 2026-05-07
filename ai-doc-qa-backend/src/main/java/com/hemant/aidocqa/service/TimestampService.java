package com.hemant.aidocqa.service;

import com.hemant.aidocqa.dto.TimestampResponseDto;

public interface TimestampService {

    TimestampResponseDto searchTimestamp(
            Long fileId,
            String topic
    );
}