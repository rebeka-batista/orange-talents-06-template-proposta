package br.com.proposta.associacartaocomproposta;

import br.com.proposta.novaproposta.Proposta;
import br.com.proposta.novaproposta.PropostaRepository;
import br.com.proposta.validator.ExistsId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;

public class VinculaPropostaComCartaoRequest {

    @Autowired
    private PropostaRepository repository;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @ExistsId(domainClass = Proposta.class, fieldName = "id")
    @NotNull
    private Long idProposta;

    private String id;

    @Deprecated
    public VinculaPropostaComCartaoRequest() {
    }

    public VinculaPropostaComCartaoRequest(Long idProposta, String id) {
        this.idProposta = idProposta;
        this.id = id;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public String getId() {
        return id;
    }

}

