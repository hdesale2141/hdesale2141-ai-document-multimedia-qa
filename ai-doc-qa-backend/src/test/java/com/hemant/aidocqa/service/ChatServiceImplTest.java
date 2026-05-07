package com.hemant.aidocqa.service;

import com.hemant.aidocqa.entity.TranscriptSegment;
import com.hemant.aidocqa.repository.TranscriptSegmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TimestampServiceImplTest {

    @Mock
    private TranscriptSegmentRepository repository;

    @InjectMocks
    private TimestampServiceImpl service;

    @Test
    void shouldReturnMatchingTimestamp() {

        TranscriptSegment segment =
                TranscriptSegment.builder()
                        .startTime(30.0)
                        .endTime(60.0)
                        .text("Machine learning concepts")
                        .build();

        when(repository.findByFileId(1L))
                .thenReturn(List.of(segment));

        var response =
                service.searchTimestamp(
                        1L,
                        "machine"
                );

        assertNotNull(response);

        assertEquals(
                30.0,
                response.getStartTime()
        );
    }

    @Test
    void shouldReturnNoMatchFound() {

        when(repository.findByFileId(1L))
                .thenReturn(List.of());

        var response =
                service.searchTimestamp(
                        1L,
                        "AI"
                );

        assertEquals(
                "No matching topic found.",
                response.getText()
        );
    }
}