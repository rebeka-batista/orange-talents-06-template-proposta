package br.com.proposta.novaproposta;

import br.com.proposta.validator.Documento;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaDto {

    @NotBlank
    @JsonProperty("nome")
    private String nome;

    @Email
    @JsonProperty("email")
    private String email;

    @Documento(domainClass = PropostaEntity.class, fieldName = "documento")
    @JsonProperty("documento")
    private String documento;

    @NotBlank
    @JsonProperty("endereco")
    private String endereco;

    @NotNull
    @Positive
    @JsonProperty("salario")
    private BigDecimal salario;

    NovaPropostaDto(){}

    public NovaPropostaDto(PropostaEntity proposta) {
        this.nome = nome;
        this.email = email;
        this.documento = documento;
        this.endereco = endereco;
        this.salario = salario;
    }


    public PropostaEntity toModel() {
        return new PropostaEntity(this.nome, this.email, this.documento, this.endereco, this.salario);
    }

}
