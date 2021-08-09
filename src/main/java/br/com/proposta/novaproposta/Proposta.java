package br.com.proposta.novaproposta;

import br.com.proposta.consultadadossolicitante.RetornoStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Proposta {

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

    private String cartao;

    public Proposta() {
    }

    public Proposta(Long idProposta,
                    @NotBlank String nome, @NotBlank @Email String email,
                    @NotNull String documento, @NotBlank String endereco,
                    @NotNull BigDecimal salario, RetornoStatus statusProposta) {
        this.idProposta = idProposta;
        this.nome = nome;
        this.email = email;
        BCryptPasswordEncoder bCrypt3 = new BCryptPasswordEncoder();
        this.documento = bCrypt3.encode(documento);
        this.endereco = endereco;
        this.salario = salario;
        this.statusProposta = statusProposta;
    }

    public Proposta(Long idProposta, String nome, String email, String documento,
                    String endereco, BigDecimal salario, RetornoStatus statusProposta,
                    String cartao) {
        this.idProposta = idProposta;
        this.nome = nome;
        this.email = email;
        BCryptPasswordEncoder bCrypt3 = new BCryptPasswordEncoder();
        this.documento = bCrypt3.encode(documento);
        this.endereco = endereco;
        this.salario = salario;
        this.statusProposta = statusProposta;
        this.cartao = cartao;
    }

    @Override
    public String toString() {
        return "Proposta: " +
                "\nId da Proposta: " + idProposta +
                "\nNome: " + nome +
                "\nEmail: " + email +
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

    public String getCartao() {
        return cartao;
    }
}
