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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

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
    public ResponseEntity<?> cadastrarProposta(@RequestBody @Valid NovaPropostaRequest novaProposta) {

        Optional<Proposta> documento = propostaRepository.findByDocumento(novaProposta.getDocumento());
        if (!documento.isPresent()) {

            var retornoApi = analise.avaliaProposta(novaProposta);

            if (novaProposta.getStatusProposta() == StatusProposta.COM_RESTRICAO) {
                novaProposta.setStatus(RetornoStatus.NAO_ELEGIVEL);
            } else {
                novaProposta.setStatus(RetornoStatus.ELEGIVEL);
            }

            Proposta proposta = novaProposta.toModel(manager);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .path("/{id}")
                    .buildAndExpand(proposta.getIdProposta())
                    .toUri();

            manager.persist(proposta);
            return ResponseEntity.status(201).body(uri);
        }
        return ResponseEntity.status(422).build();
    }

}

