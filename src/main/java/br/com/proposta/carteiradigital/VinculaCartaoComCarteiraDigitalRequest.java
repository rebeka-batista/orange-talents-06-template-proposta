package br.com.proposta.carteiradigital;

import br.com.proposta.novaproposta.Proposta;
import br.com.proposta.validator.ExistsId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class VinculaCartaoComCarteiraDigitalRequest {

    @NotBlank
    @Email
    @JsonProperty("email")
    private String email;

    @JsonProperty("associaCarteiraDigital")
    private AssociaCarteiraDigital associaCarteiraDigital;


    // @ExistsId(domainClass = Proposta.class, fieldName = "idProposta")
    @JsonProperty("idProposta")
    private Long idProposta;


    public VinculaCartaoComCarteiraDigitalRequest() {
    }

    public VinculaCartaoComCarteiraDigitalRequest(String email, Long idProposta,
                                                  AssociaCarteiraDigital associaCarteiraDigital) {
        this.email = email;
        this.associaCarteiraDigital = AssociaCarteiraDigital.PAYPAL;
        this.idProposta = idProposta;
    }

    public CarteiraDigital converter(EntityManager manager) {
        Proposta proposta = manager.find(Proposta.class, idProposta);
        return new CarteiraDigital(email, associaCarteiraDigital, proposta);
    }

    public String getEmail() {
        return email;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(Long idProposta) {
        this.idProposta = idProposta;
    }

    public AssociaCarteiraDigital getAssociaCarteiraDigital() {
        return associaCarteiraDigital;
    }

}
