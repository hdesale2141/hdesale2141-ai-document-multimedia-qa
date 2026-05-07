package com.hemant.aidocqa.controller;

import com.hemant.aidocqa.dto.TimestampResponseDto;
import com.hemant.aidocqa.service.TimestampService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/timestamps")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TimestampController {

    private final TimestampService timestampService;

    @GetMapping("/search")
    public ResponseEntity<TimestampResponseDto> searchTimestamp(
            @RequestParam Long fileId,
            @RequestParam String topic
    ) {

        return ResponseEntity.ok(
                timestampService.searchTimestamp(fileId, topic)
        );
    }
}