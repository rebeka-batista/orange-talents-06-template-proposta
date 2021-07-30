package br.com.proposta.bloqueiodocartao;

import br.com.proposta.novaproposta.Proposta;
import br.com.proposta.novaproposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/cartao")
public class BloqueioCartaoController {

    @Autowired
    private BloqueioCartaoRepository bloqueioRepository;

    @Autowired
    private PropostaRepository propostaRepository;

    @PersistenceContext
    private EntityManager manager;


    @PostMapping("/bloqueio/{idProposta}")
    @Transactional
    public ResponseEntity<?> possivelBloqueioDeCartao(@PathVariable("idProposta") Long idProposta,
                                                      @Valid BloqueioCartaoRequest bloqueioRequest, HttpServletRequest request) {
        Optional<Proposta> resultBuscaCartao = propostaRepository.findById(idProposta);
        Optional<BloqueioCartao> resultBloqueioCartao = bloqueioRepository.buscaIdProposta(idProposta);

        if (resultBloqueioCartao.isPresent()) {
            return ResponseEntity.status(422).build();
        }
        if (!resultBuscaCartao.isPresent()) {
            return ResponseEntity.status(404).build();
        }
        try {
            Proposta proposta = manager.find(Proposta.class, idProposta);
            BloqueioCartao bloqueioCartao = bloqueioRequest.converter(proposta, request);
            var salvaBloqueio = bloqueioRepository.save(bloqueioCartao);
            return ResponseEntity.ok().body(salvaBloqueio);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(400).build();
        }
    }

}

