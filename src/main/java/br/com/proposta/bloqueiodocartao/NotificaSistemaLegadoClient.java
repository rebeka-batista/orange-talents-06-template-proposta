package br.com.proposta.bloqueiodocartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "notifica-sistema-legado", url = "${contas}")
public interface NotificaSistemaLegadoClient {

    @PostMapping("/api/cartoes/{id}/bloqueios")
    Map<Object, ?> verificaBloqueio(@PathVariable("id") Long idProposta, @RequestBody SistemaBloqueioRequest sistemaBloqueioRequest);

}
