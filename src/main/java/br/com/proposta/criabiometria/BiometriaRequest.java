package br.com.proposta.criabiometria;

import br.com.proposta.novaproposta.Proposta;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class BiometriaRequest {

    @JsonProperty("numeroCartao")
    @NotBlank
    private String numeroCartao;

    @JsonProperty
    @NotBlank
    private String biometria;

    @JsonProperty("idProposta")
    @JsonIgnore
    private Long idProposta;

    @Deprecated
    public BiometriaRequest() {
    }

    public Biometria converter(EntityManager manager, Proposta proposta) {
        return new Biometria(numeroCartao, biometria, proposta);
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getBiometria() {
        return biometria;
    }

    public Long getIdProposta() {
        return idProposta;
    }

}
