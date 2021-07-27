package br.com.proposta.novaproposta;

import br.com.proposta.consultadadossolicitante.RetornoStatus;
import br.com.proposta.consultadadossolicitante.StatusProposta;
import br.com.proposta.validator.Documento;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaRequest {

    @NotBlank
    @JsonProperty("nome")
    private String nome;

    @NotBlank
    @Email
    @JsonProperty("email")
    private String email;

    @Documento(domainClass = Proposta.class, fieldName = "documento")
    @JsonProperty("documento")
    private String documento;

    @NotBlank
    @JsonProperty("endereco")
    private String endereco;

    @NotNull
    @Positive
    @JsonProperty("salario")
    private BigDecimal salario;

    @JsonProperty("idProposta")
    private Long idProposta;

    private RetornoStatus retornoStatus;
    private StatusProposta statusProposta;

    NovaPropostaRequest() {
    }

    public NovaPropostaRequest(@NotNull Long idProposta, @NotBlank String nome, @NotBlank String documento,
                               @NotBlank @Email String email, @NotNull BigDecimal salario,
                               @NotNull StatusProposta statusProposta, @NotBlank String endereco) {

        this.idProposta = idProposta;
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.salario = salario;
        this.statusProposta = statusProposta;
        this.endereco = endereco;
    }

    public NovaPropostaRequest(@NotNull Long idProposta, @NotBlank String nome, @NotBlank String documento,
                               @NotBlank @Email String email, @NotNull BigDecimal salario, @NotBlank String endereco,
                               @NotNull RetornoStatus status) {
        this.idProposta = idProposta;
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.salario = salario;
        this.endereco = endereco;
        this.retornoStatus = status;
    }


    public Proposta toModel(EntityManager manager) {
        return new Proposta(this.idProposta, this.nome, this.email, this.documento, this.endereco, this.salario, this.retornoStatus);
    }

    public void setStatus(RetornoStatus status) {
        this.retornoStatus = status;
    }


    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public RetornoStatus getRetornoStatus() {
        return retornoStatus;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }

    public void setRetornoStatus(RetornoStatus retornoStatus) {
        this.retornoStatus = retornoStatus;
    }

}
