package br.com.proposta.acompanhamentoproposta;

import br.com.proposta.novaproposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proposta")
public class AcompanhamentoPropostaController {

    @Autowired
    private PropostaRepository repository;

    @GetMapping("/acompanhamento/{id}")
    public ResponseEntity<?> acompanharProposta(@PathVariable Long id) {
        var lista = repository.findById(id);
        if (lista.isPresent()) {
            return ResponseEntity.ok().body(lista);
        }
        return ResponseEntity.notFound().build();
    }

}
