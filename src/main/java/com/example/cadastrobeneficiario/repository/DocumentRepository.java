package com.example.cadastrobeneficiario.repository;

import com.example.cadastrobeneficiario.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Override
    Optional<Document> findById(Long id);
}
