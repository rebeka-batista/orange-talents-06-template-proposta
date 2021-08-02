package br.com.proposta.bloqueiodocartao;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloqueioCartaoRepository extends JpaRepository<BloqueioCartao, Long> {

    @Query("SELECT 1 FROM BloqueioCartao WHERE proposta_id_proposta = :idProposta")
    Optional<BloqueioCartao> buscaIdProposta(@Param("idProposta") Long idProposta);

}
