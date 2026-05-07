package com.hemant.aidocqa.repository;

import com.hemant.aidocqa.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    FileEntity getById(Long id);
}
