package br.com.proposta.avisodeviagem;

import br.com.proposta.novaproposta.Proposta;
import br.com.proposta.novaproposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AvisoViagemController {

    @Autowired
    private AvisoViagemRepository avisoViagemRepository;

    @Autowired
    private PropostaRepository propostaRepository;

    @PersistenceContext
    private EntityManager manager;


    @PostMapping("/aviso-viagem/{idProposta}")
    @Transactional
    public ResponseEntity<?> criaAvisoViagem (@PathVariable("idProposta") Long idProposta,
                                              @RequestBody @Valid AvisoViagemRequest avisoViagemRequest, HttpServletRequest request){

        Optional<Proposta> checaProposta = propostaRepository.findById(idProposta);

        if(checaProposta.isPresent()){
            Proposta proposta = manager.find(Proposta.class, idProposta);
            AvisoViagem avisoviagem = avisoViagemRequest.converter(proposta, request);
            var avisoViagem = avisoViagemRepository.save(avisoviagem);
            return ResponseEntity.ok().body(avisoViagem);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
