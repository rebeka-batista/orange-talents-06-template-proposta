package br.com.proposta.associacartaocomproposta;

import br.com.proposta.novaproposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class CriaCartao {

    @Autowired
    private PropostaRepository repository;

    @Scheduled(fixedDelay = 5000)
    public void criaNovoCartaoMedianteProposta() {
        try {
            Object lista = repository.buscaIdPropostaECartao();
            System.out.println(lista);
        } catch (IllegalThreadStateException ex) {
            ex.getCause();
        }
    }

}

