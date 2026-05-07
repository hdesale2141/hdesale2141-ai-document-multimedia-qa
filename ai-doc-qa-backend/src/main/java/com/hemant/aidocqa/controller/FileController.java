package com.hemant.aidocqa.controller;

import com.hemant.aidocqa.dto.FileResponseDto;
import com.hemant.aidocqa.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<FileResponseDto> uploadFile(
            @RequestParam("file") MultipartFile file
    ) {

        return ResponseEntity.ok(fileService.uploadFile(file));
    }

    @GetMapping("/get/{id}")
    public FileResponseDto getFileById(@PathVariable Long id){
        return fileService.getFileById(id);
    }
}