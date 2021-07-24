package br.com.proposta.novaproposta;

import br.com.proposta.consultadadossolicitante.AnaliseDadosClient;
import br.com.proposta.consultadadossolicitante.RetornoStatus;
import br.com.proposta.consultadadossolicitante.StatusProposta;
import feign.FeignException;
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

        Optional<PropostaEntity> documento = propostaRepository.findByDocumento(novaProposta.getDocumento());
        if (!documento.isPresent()) {
            var retornoApi = analise.avaliaProposta(novaProposta);
            PropostaEntity proposta = novaProposta.toModel(manager);
            geraURI(proposta);
            try {
                defineElegibilidade(novaProposta);
                manager.persist(proposta);
                return ResponseEntity.status(201).body(retornoApi);

            } catch (FeignException e) {
                defineElegibilidade(novaProposta);
                return ResponseEntity.status(422).build();
            }
        }

        return ResponseEntity.status(422).build();
    }


    private void defineElegibilidade(NovaPropostaRequest request) {
        if (request.getStatusProposta() == StatusProposta.COM_RESTRICAO) {
            request.setStatus(RetornoStatus.NAO_ELEGIVEL);
        } else {
            request.setStatus(RetornoStatus.ELEGIVEL);
        }
    }

    private void geraURI(PropostaEntity proposta) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(proposta.getIdProposta())
                .toUri();
    }


}

