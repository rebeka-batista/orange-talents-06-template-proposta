package br.com.proposta.novaproposta;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity(name = "proposta")
public class PropostaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public PropostaEntity(){}

    public PropostaEntity(@NotBlank String nome, @NotBlank @Email String email,
                          @NotNull String documento, @NotBlank String endereco,
                          @NotNull BigDecimal salario) {
        this.nome = nome;
        this.email = email;
        this.documento = documento;
        this.endereco = endereco;
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Proposta: " +
                "\nNome: " + nome +
                ",\nEmail: " + email +
                "\nDocumento: " + documento +
                "\nEndereco: " + endereco +
                "\nSalario: " + salario;
    }
}
