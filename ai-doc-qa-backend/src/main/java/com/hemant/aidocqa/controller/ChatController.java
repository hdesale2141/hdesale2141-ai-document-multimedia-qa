package com.hemant.aidocqa.controller;

import com.hemant.aidocqa.dto.ChatRequestDto;
import com.hemant.aidocqa.dto.ChatResponseDto;
import com.hemant.aidocqa.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/ask")
    public ResponseEntity<ChatResponseDto> askQuestion(
            @RequestBody ChatRequestDto request
    ) {

        return ResponseEntity.ok(
                chatService.askQuestion(request)
        );
    }
}