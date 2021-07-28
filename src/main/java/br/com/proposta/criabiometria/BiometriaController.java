package br.com.proposta.criabiometria;

import br.com.proposta.novaproposta.Proposta;
import br.com.proposta.novaproposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("/biometria")
public class BiometriaController {

    @Autowired
    private PropostaRepository repository;

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/cadastro/{id}")
    @Transactional
    public ResponseEntity<?> criaBiometria(@PathVariable("id") Long id, @RequestBody @Valid BiometriaRequest request) {

        Proposta proposta = manager.find(Proposta.class, id);
        Optional<Proposta> numeroCartao = repository.findByCartao(request.getNumeroCartao());
        Biometria biometria = request.converter(manager, proposta);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{biometria}")
                .buildAndExpand(Base64.getEncoder().encodeToString(request.getBiometria().getBytes()))
                .toUri();

        if (numeroCartao.isPresent()) {
            manager.persist(biometria);
            return ResponseEntity.status(201).header(HttpHeaders.LOCATION, String.valueOf(uri)).build();

        } else {
            return ResponseEntity.status(404).build();
        }
    }

}

