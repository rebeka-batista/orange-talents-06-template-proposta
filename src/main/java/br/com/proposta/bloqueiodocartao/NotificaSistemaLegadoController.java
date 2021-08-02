package br.com.proposta.bloqueiodocartao;

import br.com.proposta.novaproposta.Proposta;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/cartao")
public class NotificaSistemaLegadoController {

    @Autowired
    private NotificaSistemaLegadoClient analise;

    @Autowired
    private BloqueioCartaoRepository bloqueioCartaoRepository;

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/notifica-sistema-legado/{idProposta}")
    @Transactional
    public ResponseEntity<?> notificaSistemaLegado(@PathVariable("idProposta") Long idProposta,
                                                   HttpServletRequest request,
                                                   BloqueioCartaoRequest bloqueioCartaoRequest,
                                                   @RequestBody SistemaBloqueioRequest sistemaBloqueioRequest) {
        var resultadoBloqueio = analise.verificaBloqueio(idProposta, sistemaBloqueioRequest).get("resultado");

        try {
            Proposta proposta = manager.find(Proposta.class, idProposta);
            return ResponseEntity.ok().body(resultadoBloqueio);

        } catch (FeignException ex) {
            ex.getCause();
        }
        return ResponseEntity.status(400).build();

    }

}
