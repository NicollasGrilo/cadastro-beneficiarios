package com.example.cadastrobeneficiario.repository;

import com.example.cadastrobeneficiario.model.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {

    Beneficiario findByName(String name);

    void deleteById(Long id);
    @Override
    Optional<Beneficiario> findById(Long id);
}
