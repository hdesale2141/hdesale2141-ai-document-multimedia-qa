package com.hemant.aidocqa.service;

import com.hemant.aidocqa.ai.OpenAIService;
import com.hemant.aidocqa.dto.ChatRequestDto;
import com.hemant.aidocqa.dto.ChatResponseDto;
import com.hemant.aidocqa.entity.FileEntity;
import com.hemant.aidocqa.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final FileRepository fileRepository;

    private final OpenAIService openAIService;

    @Override
    public ChatResponseDto askQuestion(ChatRequestDto request) {

        FileEntity fileEntity = fileRepository.findById(request.getFileId())
                .orElseThrow(() -> new RuntimeException("File not found"));

        String extractedText = fileEntity.getExtractedText();

        if (extractedText == null || extractedText.isBlank()) {

            return ChatResponseDto.builder()
                    .answer("No extracted text found for this file.")
                    .build();
        }

        String limitedContext = extractedText.length() > 5000
                ? extractedText.substring(0, 5000)
                : extractedText;

        String answer = openAIService.askQuestion(
                limitedContext,
                request.getQuestion()
        );

        return ChatResponseDto.builder()
                .answer(answer)
                .build();
    }
}