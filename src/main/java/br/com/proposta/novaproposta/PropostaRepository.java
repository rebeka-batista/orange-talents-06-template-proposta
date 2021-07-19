package br.com.proposta.novaproposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<PropostaEntity, Long> {
    Optional<PropostaEntity> findByDocumento(String documento);
}
