package br.com.proposta.associacartaocomproposta;

import br.com.proposta.novaproposta.Proposta;
import br.com.proposta.novaproposta.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/proposta")
public class AssociaCartaoPropostaController {

    @Autowired
    private PropostaRepository repository;

    @Autowired
    private AssociaCartaoComPropostaClient associaCartaoComProposta;

    @PostMapping(value = "associa-cartao")
    @Transactional
    public ResponseEntity<?> associandoPropostaCartao(@RequestBody VinculaPropostaComCartaoRequest request) {
        Map<Object, ?> associaPropostaCartao = associaCartaoComProposta.associarCartaoComProposta(request);

        try {
            Optional<Proposta> proposta = repository.findByIdPropostaAndCartao(request.getIdProposta(), request.getId());
            if (proposta.isPresent()) {
                repository.saveCartao((String) associaPropostaCartao.get("id"), request.getIdProposta());
                return ResponseEntity.ok().build();
            }
        } catch (FeignException e) {
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(400).build();
    }

}
