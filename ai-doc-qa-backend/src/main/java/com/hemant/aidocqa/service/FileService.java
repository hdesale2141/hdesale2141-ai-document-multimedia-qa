package com.hemant.aidocqa.service;

import com.hemant.aidocqa.dto.FileResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    FileResponseDto uploadFile(MultipartFile file);
    FileResponseDto getFileById(Long id);
}