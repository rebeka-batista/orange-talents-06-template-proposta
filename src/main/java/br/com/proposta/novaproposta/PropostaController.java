package br.com.proposta.novaproposta;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity<?> cadastrarProposta(@RequestBody @Valid NovaPropostaDto novaProposta) {
        PropostaEntity proposta = novaProposta.toModel();
        manager.persist(proposta);
        if (novaProposta == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(URI.create("http://localhost:8080/proposta/cadastro")).build();
    }

}

