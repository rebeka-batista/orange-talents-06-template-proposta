package br.com.proposta.associacartaocomproposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "associacartao", url = "http://localhost:8888")
public interface AssociaCartaoComPropostaClient {

    @GetMapping("/api/cartoes")
    Map<Object, ?> associarCartaoComProposta(@RequestBody VinculaPropostaComCartaoRequest request);

}
