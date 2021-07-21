package br.com.proposta.consultadadossolicitante;

import br.com.proposta.novaproposta.PropostaEntity;
import br.com.proposta.validator.Documento;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AnaliseDadosRequest {

    @Documento(domainClass = PropostaEntity.class, fieldName = "documento")
    @NotNull
    @JsonProperty("documento")
    private String documento;

    @JsonProperty("id_proposta")
    @NotNull
    private Long idProposta;

    @NotBlank
    @JsonProperty("nome")
    private String nome;

    public AnaliseDadosRequest() {
    }

    public AnaliseDadosRequest(@NotNull @Valid PropostaEntity proposta) {
        this.documento = proposta.getDocumento();
        this.idProposta = proposta.getIdProposta();
        this.nome = proposta.getNome();
    }

    public String getDocumento() {
        return documento;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public String getNome() {
        return nome;
    }
}
