package com.hemant.aidocqa.service;

import com.hemant.aidocqa.dto.TimestampResponseDto;
import com.hemant.aidocqa.entity.TranscriptSegment;
import com.hemant.aidocqa.repository.TranscriptSegmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimestampServiceImpl implements TimestampService {

    private final TranscriptSegmentRepository repository;

    @Override
    public TimestampResponseDto searchTimestamp(
            Long fileId,
            String topic
    ) {

        List<TranscriptSegment> segments =
                repository.findByFileId(fileId);

        for (TranscriptSegment segment : segments) {

            if (segment.getText()
                    .toLowerCase()
                    .contains(topic.toLowerCase())) {

                return TimestampResponseDto.builder()
                        .startTime(segment.getStartTime())
                        .endTime(segment.getEndTime())
                        .text(segment.getText())
                        .build();
            }
        }

        return TimestampResponseDto.builder()
                .text("No matching topic found.")
                .build();
    }
}