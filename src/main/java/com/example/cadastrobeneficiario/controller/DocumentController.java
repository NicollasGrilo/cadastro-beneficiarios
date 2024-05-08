package com.example.cadastrobeneficiario.controller;

import com.example.cadastrobeneficiario.model.Beneficiario;
import com.example.cadastrobeneficiario.model.Document;
import com.example.cadastrobeneficiario.repository.BeneficiarioRepository;
import com.example.cadastrobeneficiario.repository.DocumentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/documentos")
public class DocumentController {

    private final BeneficiarioRepository beneficiarioRepository;

    private final DocumentRepository documentRepository;
    @ResponseStatus(OK)
    @PostMapping("/cadastrar")
    public ResponseEntity<Document> cadastrar(@RequestBody Document document){

        try {
            Document documentDB = documentRepository.save(document);
            return new ResponseEntity<>(documentDB, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
