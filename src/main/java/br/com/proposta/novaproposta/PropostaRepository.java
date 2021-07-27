package br.com.proposta.novaproposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    Optional<Proposta> findByDocumento(String documento);

    // aqui e para alterar uma informacao
    @Modifying
    @Query(value = "UPDATE Proposta p SET p.cartao = ?1 WHERE p.idProposta = ?2")
    void saveCartao(String cartao, Long idProposta);


    Optional<Proposta> findByIdPropostaAndCartao(Long idProposta, String id);


    @Query(value = "SELECT p FROM Proposta p WHERE p.cartao is null AND p.id is not null")
    List<Proposta> buscaIdPropostaECartao();

}
