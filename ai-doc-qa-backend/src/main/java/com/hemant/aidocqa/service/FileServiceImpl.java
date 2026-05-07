package com.hemant.aidocqa.service;

import com.hemant.aidocqa.ai.OpenAIService;
import com.hemant.aidocqa.dto.FileResponseDto;
import com.hemant.aidocqa.entity.FileEntity;
import com.hemant.aidocqa.repository.FileRepository;
import com.hemant.aidocqa.utils.PdfExtractorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.hemant.aidocqa.ai.WhisperService;
import com.hemant.aidocqa.entity.TranscriptSegment;
import com.hemant.aidocqa.repository.TranscriptSegmentRepository;
import com.hemant.aidocqa.utils.VideoAudioExtractorUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final OpenAIService openAIService;
    private final WhisperService whisperService;
    private final TranscriptSegmentRepository transcriptSegmentRepository;
//    private PdfExtractorUtil pdfExtractorUtil;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public FileResponseDto uploadFile(MultipartFile file) {

        try {

            File directory = new File(uploadDir);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            String originalFileName = file.getOriginalFilename();

            String uniqueFileName = UUID.randomUUID() + "_" + originalFileName;

            Path filePath = Paths.get(uploadDir, uniqueFileName);

            Files.copy(file.getInputStream(), filePath);
            String extractedText = null;
            String summary = null;

            if (file.getContentType() != null &&
                    file.getContentType().equals("application/pdf")) {

                extractedText = PdfExtractorUtil.extractText(filePath.toString());

                String limitedText = extractedText.length() > 4000
                        ? extractedText.substring(0, 4000)
                        : extractedText;

                summary = openAIService.generateSummary(limitedText);
            }


            FileEntity fileEntity = FileEntity.builder()
                    .fileName(originalFileName)
                    .fileType(file.getContentType())
                    .filePath(filePath.toString())
                    .uploadedAt(LocalDateTime.now())
                    .extractedText(extractedText)
                    .summary(summary)
                    .build();

            FileEntity savedFile = fileRepository.save(fileEntity);

            if (file.getContentType() != null &&
                    (file.getContentType().startsWith("audio")
                            || file.getContentType().startsWith("video"))) {

                String audioPath = filePath.toString();

                if (file.getContentType().startsWith("video")) {

                    audioPath = uploadDir + "/"
                            + UUID.randomUUID()
                            + "_audio.mp3";

                    VideoAudioExtractorUtil.extractAudio(
                            filePath.toString(),
                            audioPath
                    );
                }

                var segments = whisperService.transcribe(audioPath);

                for (var segment : segments) {

                    TranscriptSegment transcriptSegment =
                            TranscriptSegment.builder()
                                    .startTime(
                                            Double.parseDouble(
                                                    segment.get("start").toString()
                                            )
                                    )
                                    .endTime(
                                            Double.parseDouble(
                                                    segment.get("end").toString()
                                            )
                                    )
                                    .text(segment.get("text").toString())
                                    .file(savedFile)
                                    .build();

                    transcriptSegmentRepository.save(transcriptSegment);
                }
            }
            String normalizedPath =
                    savedFile.getFilePath()
                            .replace("\\", "/");

            return FileResponseDto.builder()
                    .id(savedFile.getId())
                    .fileName(savedFile.getFileName())
                    .fileType(savedFile.getFileType())
                    .summary(savedFile.getSummary())
                    .extractedText(savedFile.getExtractedText())
                    .filePath(normalizedPath)
                    .build();

        } catch (IOException e) {
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }
    }



    @Override
    public FileResponseDto getFileById(Long id) {
        FileEntity file = fileRepository.getById(id);
        if(file != null){
            return FileResponseDto.builder()
                    .id(file.getId())
                    .fileType(file.getFileType())
                    .fileName(file.getFileName())
                    .summary(file.getSummary())
                    .extractedText(file.getExtractedText())
                    .build();
        }
        return null;
    }
}