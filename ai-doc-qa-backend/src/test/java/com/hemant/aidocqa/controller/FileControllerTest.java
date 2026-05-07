package com.hemant.aidocqa.controller;

import com.hemant.aidocqa.dto.FileResponseDto;
import com.hemant.aidocqa.service.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FileController.class)
class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FileService fileService;

    @Test
    void shouldUploadFileSuccessfully() throws Exception {

        MockMultipartFile file =
                new MockMultipartFile(
                        "file",
                        "test.pdf",
                        "application/pdf",
                        "sample content".getBytes()
                );

        when(fileService.uploadFile(any()))
                .thenReturn(
                        FileResponseDto.builder()
                                .id(1L)
                                .fileName("test.pdf")
                                .build()
                );

        mockMvc.perform(
                        multipart("/api/files/upload")
                                .file(file)
                )
                .andExpect(status().isOk());
    }
}