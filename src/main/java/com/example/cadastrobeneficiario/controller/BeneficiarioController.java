package com.example.cadastrobeneficiario.controller;

import com.example.cadastrobeneficiario.model.Beneficiario;
import com.example.cadastrobeneficiario.model.Document;
import com.example.cadastrobeneficiario.model.ErrorMessage;
import com.example.cadastrobeneficiario.repository.BeneficiarioRepository;
import com.example.cadastrobeneficiario.repository.DocumentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/beneficiarios")
@Tag(name = "Beneficiarios")
public class BeneficiarioController {

    private final BeneficiarioRepository beneficiarioRepository;

    private final DocumentRepository documentRepository;

    @ResponseStatus(OK)
    @GetMapping("/listarCadastros")
    @Operation(summary = "Lista todos os beneficiarios cadastrados")
    public ResponseEntity<List<Beneficiario>> listarBeneficiarios(){

        List<Beneficiario> beneficiarios = beneficiarioRepository.findAll();

        return ResponseEntity.ok(beneficiarios);
    }

    @ResponseStatus(OK)
    @GetMapping("/documents/{id}")
    @Operation(summary = "Lista todos os documentos de um beneficiario a partir de seu id")
    public ResponseEntity<?> listarDocumentosBeneficiario(@PathVariable Long id){

        Optional<Beneficiario> byId = beneficiarioRepository.findById(id);

        if (!byId.isPresent()){
            ErrorMessage errorMessage = new ErrorMessage();
            String message = String.format("Beneficiário com o id %s nao encontrado!!", id);
            errorMessage.setMessage(message);
            return ResponseEntity.badRequest().body(errorMessage);
        }

        Beneficiario documents = byId.get();

        return ResponseEntity.ok(documents.getDocumentos());
    }

    @PostMapping("/cadastrar")
    @Operation(summary = "Cadastra um beneficiario junto com seus documentos")
    public ResponseEntity<?> cadastrar(@RequestBody Beneficiario beneficiario) {

        beneficiario.setDataInclusao(LocalDateTime.now());
        beneficiario.setDataAtualizacao(LocalDateTime.now());

        try {
            // novo plano de saude de um beneficiario
            Beneficiario bnfPlano = new Beneficiario(beneficiario.getNome(),
                    beneficiario.getTelefone(), beneficiario.getDataNascimento(),
                    beneficiario.getDataInclusao(), beneficiario.getDataAtualizacao());

            // criando um arrayList dos documentos
            List<Document> documentList = new ArrayList<>();
            for (Document bnfDocument : beneficiario.getDocumentos()){

                bnfDocument.setDataInclusao(LocalDateTime.now());
                bnfDocument.setDataAtualizacao(LocalDateTime.now());

                // novo documento
                Document document = new Document(bnfDocument.getTipoDocumento(), bnfDocument.getDescricao(),
                        bnfDocument.getDataInclusao(), bnfDocument.getDataAtualizacao());

                document.setBeneficiario(bnfPlano);

                documentList.add(document);
            }

            bnfPlano.setDocumentos(documentList);

            Beneficiario bnfDB = beneficiarioRepository.save(bnfPlano);

            return ResponseEntity.ok(bnfDB);
        } catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }

    @ResponseStatus(OK)
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados cadastrais de um beneficiario")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Beneficiario beneficiario){

        Optional<Beneficiario> byId = beneficiarioRepository.findById(id);

        if (!byId.isPresent()){
            ErrorMessage errorMessage = new ErrorMessage();
            String message = String.format("Beneficiário com o id %s nao encontrado!!", id);
            errorMessage.setMessage(message);
            return ResponseEntity.badRequest().body(errorMessage);
        }

        return byId.map(beneficiarioAlter -> {
            beneficiarioAlter.setNome(beneficiario.getNome());
            beneficiarioAlter.setTelefone(beneficiario.getTelefone());
            beneficiarioAlter.setDataNascimento(beneficiario.getDataNascimento());
            beneficiarioAlter.setDataAtualizacao(LocalDateTime.now());
            Beneficiario beneficiarioDB = beneficiarioRepository.save(beneficiarioAlter);
            return ResponseEntity.ok(beneficiarioDB);
        }).orElse(ResponseEntity.notFound().build());
    }

    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um beneficiario")
    public ResponseEntity<?> delete(@PathVariable Long id){

        Optional<Beneficiario> beneficiario = beneficiarioRepository.findById(id);

        if (!beneficiario.isPresent()){
            ErrorMessage errorMessage = new ErrorMessage();
            String message = String.format("Beneficiário com o id %s nao encontrado!!", id);
            errorMessage.setMessage(message);
            return ResponseEntity.badRequest().body(errorMessage);
        }

        beneficiarioRepository.deleteById(id);

        return ResponseEntity.ok(beneficiario);
    }
}
