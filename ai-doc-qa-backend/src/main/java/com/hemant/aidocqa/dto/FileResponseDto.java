package com.hemant.aidocqa.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileResponseDto {

    private Long id;
    private String fileName;
    private String fileType;
    private String filePath;
    private String summary;
    private String extractedText;
}