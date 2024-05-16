package com.example.cadastrobeneficiario.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "beneficiario")
@JsonPropertyOrder({ "id", "nome", "telefone", "dataNascimento", "dataInclusao", "dataAtualizacao", "documentos"})
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("Id")
    @Column(name = "ID")
    private Long id;
    @JsonProperty("nome")
    @Column(name = "name")
    private String name;
    @JsonProperty("telefone")
    @Column(name = "telefone")
    private Long telefone;
    @JsonProperty("dataNascimento")
    @Column(name = "dataNascimento")
    private String dataNascimento;
    @JsonProperty("dataInclusao")
    @Column(name = "dataInclusao")
    private LocalDateTime dataInclusao;
    @JsonProperty("dataAtualizacao")
    @Column(name = "dataAtualizacao")
    private LocalDateTime dataAtualizacao;

    @JsonProperty("documentos")
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "beneficiario", cascade = CascadeType.ALL)
    private List<Document> documentos;

    public Beneficiario() {

    }
    public Beneficiario(String name, Long telefone, String dataNascimento, LocalDateTime dataInclusao, LocalDateTime dataAtualizacao) {
        this.name = name;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return name;
    }

    public void setNome(String name) {
        this.name = name;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDateTime getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDateTime dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public List<Document> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Document> documentos) {
        this.documentos = documentos;
    }

    @Override
    public String toString() {
        return "Beneficiario{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telefone=" + telefone +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", dataInclusao='" + dataInclusao + '\'' +
                ", dataAtualizacao='" + dataAtualizacao + '\'' +
                ", documentos=" + documentos +
                '}';
    }
}
