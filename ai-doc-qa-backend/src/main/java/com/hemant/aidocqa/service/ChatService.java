package com.hemant.aidocqa.service;

import com.hemant.aidocqa.dto.ChatRequestDto;
import com.hemant.aidocqa.dto.ChatResponseDto;

public interface ChatService {

    ChatResponseDto askQuestion(ChatRequestDto request);
}