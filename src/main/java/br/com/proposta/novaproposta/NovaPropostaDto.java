package br.com.proposta.novaproposta;

import br.com.proposta.consultadadossolicitante.RetornoStatus;
import br.com.proposta.consultadadossolicitante.StatusProposta;
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

    @JsonProperty("idProposta")
    private Long idProposta;

    private RetornoStatus retornoStatus;
    private StatusProposta statusProposta;

    NovaPropostaDto() {
    }

    public NovaPropostaDto(String nome, String email, String documento,
                           String endereco, BigDecimal salario, Long idProposta, StatusProposta statusProposta) {
        this.idProposta = idProposta;
        this.nome = nome;
        this.email = email;
        this.documento = documento;
        this.endereco = endereco;
        this.salario = salario;
        this.idProposta = idProposta;
        this.statusProposta = statusProposta;
    }

    public NovaPropostaDto(String nome, String email, String documento,
                           String endereco, BigDecimal salario, Long idProposta, RetornoStatus status) {
        this.idProposta = idProposta;
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.salario = salario;
        this.endereco = endereco;
        this.retornoStatus = status;
    }


    public PropostaEntity toModel() {
        return new PropostaEntity(this.idProposta, this.nome, this.email, this.documento, this.endereco, this.salario, this.retornoStatus);
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