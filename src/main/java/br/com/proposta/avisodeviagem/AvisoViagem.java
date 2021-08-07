package br.com.proposta.avisodeviagem;

import br.com.proposta.novaproposta.Proposta;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class AvisoViagem {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idAvisoViagem;

    @Column(nullable = false)
    private LocalDate instanteViagem;

    @Column(nullable = false)
    private String ipClient;

    @Column(nullable = false)
    private String userAgent;

    @Column(nullable = false)
    private String destinoViagem;

    @Column(nullable = false)
    private LocalDate terminoViagem;

    @ManyToOne
    private Proposta proposta;

    public AvisoViagem(LocalDate instanteViagem, String ipClient, String userAgent,
                       String destinoViagem, LocalDate terminoViagem, Proposta proposta) {
        this.instanteViagem = instanteViagem;
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.destinoViagem = destinoViagem;
        this.terminoViagem = terminoViagem;
        this.proposta = proposta;
    }

    public Proposta getProposta() {
        return proposta;
    }
}
