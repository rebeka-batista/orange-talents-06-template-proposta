package br.com.proposta.consultadadossolicitante;

import br.com.proposta.novaproposta.NovaPropostaRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "analise-proposta", url = "${analise}")
public interface AnaliseDadosClient {

    @PostMapping("/api/solicitacao")
    Map<Object, ?> avaliaProposta(@RequestBody NovaPropostaRequest request);
}
