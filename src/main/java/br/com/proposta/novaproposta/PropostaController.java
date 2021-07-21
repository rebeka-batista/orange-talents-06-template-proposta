package br.com.proposta.novaproposta;

import br.com.proposta.consultadadossolicitante.AnaliseDadosClient;
import br.com.proposta.consultadadossolicitante.RetornoStatus;
import br.com.proposta.consultadadossolicitante.StatusProposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private AnaliseDadosClient analise;

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity<?> cadastrarProposta(@RequestBody @Valid NovaPropostaDto novaProposta) {

        if (possivelProposta(novaProposta.getDocumento())) {
            return ResponseEntity.unprocessableEntity().build();
        }

        var retornoApi = analise.avaliaProposta(novaProposta);
        System.out.println(retornoApi);
        if (novaProposta.getStatusProposta() == StatusProposta.COM_RESTRICAO) {
            novaProposta.setRetornoStatus(RetornoStatus.NAO_ELEGIVEL);
        } else {
            novaProposta.setRetornoStatus(RetornoStatus.ELEGIVEL);
        }

        PropostaEntity proposta = novaProposta.toModel();
        manager.persist(proposta);
        return ResponseEntity.status(201).body(retornoApi);
    }

    private Boolean possivelProposta(String documento) {
        return propostaRepository.findByDocumento(documento).isPresent();
    }

}

