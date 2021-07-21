package br.com.proposta.novaproposta;

import br.com.proposta.consultadadossolicitante.RetornoStatus;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity(name = "proposta")
public class PropostaEntity {

    @Id
    private Long idProposta;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "documento", nullable = false)
    private String documento;

    @Column(name = "endereco", nullable = false)
    private String endereco;

    @Positive
    @Column(name = "salario", nullable = false)
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    private RetornoStatus statusProposta;

    public PropostaEntity() {
    }

    public PropostaEntity(Long idProposta,
                          @NotBlank String nome, @NotBlank @Email String email,
                          @NotNull String documento, @NotBlank String endereco,
                          @NotNull BigDecimal salario, RetornoStatus statusProposta) {
        this.idProposta = idProposta;
        this.nome = nome;
        this.email = email;
        this.documento = documento;
        this.endereco = endereco;
        this.salario = salario;
        this.statusProposta = statusProposta;
    }

    @Override
    public String toString() {
        return "Proposta: " +
                "\nId da Proposta: " + idProposta +
                "\nNome: " + nome +
                ",\nEmail: " + email +
                "\nDocumento: " + documento +
                "\nEndereco: " + endereco +
                "\nSalario: " + salario;
    }


    public Long getIdProposta() {
        return idProposta;
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

    public RetornoStatus getStatusProposta() {
        return statusProposta;
    }
}
