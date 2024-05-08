package com.example.cadastrobeneficiario.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "beneficiario")
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "telefone")
    private Long telefone;
    @Column(name = "dataNascimento")
    private String dataNascimento;
    @Column(name = "dataInclusao")
    private String dataInclusao;
    @Column(name = "dataAtualizacao")
    private String dataAtualizacao;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "beneficiario", cascade = CascadeType.ALL)
    private List<Document> documentos;

    public Beneficiario() {

    }
    public Beneficiario(String name, Long telefone, String dataNascimento, String dataInclusao, String dataAtualizacao) {
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

    public String getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(String dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public String getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(String dataAtualizacao) {
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
