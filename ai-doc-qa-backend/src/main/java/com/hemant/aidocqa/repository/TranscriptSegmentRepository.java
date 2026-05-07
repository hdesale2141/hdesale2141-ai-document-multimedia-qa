package com.hemant.aidocqa.repository;

import com.hemant.aidocqa.entity.TranscriptSegment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TranscriptSegmentRepository
        extends JpaRepository<TranscriptSegment, Long> {

    List<TranscriptSegment> findByFileId(Long fileId);
}