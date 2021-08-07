package br.com.proposta.carteiradigital;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraDigitalRepository extends JpaRepository<CarteiraDigital, Long> {

    @Query("SELECT 1 FROM CarteiraDigital WHERE proposta_id_proposta = :idProposta")
    Optional<CarteiraDigital> buscaIdProposta(@Param("idProposta") Long idProposta);


}
