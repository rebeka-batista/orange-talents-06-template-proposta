package br.com.proposta.consultadadossolicitante;

import br.com.proposta.novaproposta.NovaPropostaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "analise-proposta", url = "http://localhost:9999/")
public interface AnaliseDadosClient {

    @PostMapping("api/solicitacao")
    Map<Object, String> avaliaProposta(@RequestBody NovaPropostaDto request);
}
