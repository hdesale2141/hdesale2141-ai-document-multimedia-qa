package com.hemant.aidocqa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hemant.aidocqa.dto.ChatRequestDto;
import com.hemant.aidocqa.dto.ChatResponseDto;
import com.hemant.aidocqa.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChatController.class)

class ChatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ChatService chatService;

    private final ObjectMapper objectMapper =
            new ObjectMapper();

    @Test
    void shouldReturnChatResponse() throws Exception {

        ChatRequestDto request =
                new ChatRequestDto();

        request.setFileId(1L);

        request.setQuestion("What is AI?");

        when(chatService.askQuestion(any()))
                .thenReturn(
                        ChatResponseDto.builder()
                                .answer("Artificial Intelligence")
                                .build()
                );

        mockMvc.perform(
                        post("/api/chat/ask")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(
                                                request
                                        )
                                )
                )
                .andExpect(status().isOk());
    }
}