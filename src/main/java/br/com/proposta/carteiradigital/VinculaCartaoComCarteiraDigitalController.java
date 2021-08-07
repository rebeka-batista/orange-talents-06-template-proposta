package br.com.proposta.carteiradigital;

import br.com.proposta.novaproposta.Proposta;
import br.com.proposta.novaproposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class VinculaCartaoComCarteiraDigitalController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private CarteiraDigitalRepository associaCartaoRepository;

    @PostMapping("/vincula-cartao/{idProposta}")
    @Transactional
    public ResponseEntity<?> associaCarteira(@PathVariable("idProposta") Long idProposta,
                                             @RequestBody @Valid VinculaCartaoComCarteiraDigitalRequest request) {
        Optional<CarteiraDigital> buscandoAssociacao = associaCartaoRepository.buscaIdProposta(idProposta);

        if (buscandoAssociacao.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        CarteiraDigital associaCartaoCarteira = request.converter(manager);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(request.getAssociaCarteiraDigital())
                .toUri();

        Optional<Proposta> proposta = propostaRepository.findById(idProposta);

        if (proposta.isPresent()) {
            manager.persist(associaCartaoCarteira);
            return ResponseEntity.status(201).header(HttpHeaders.LOCATION, String.valueOf(uri)).build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}

