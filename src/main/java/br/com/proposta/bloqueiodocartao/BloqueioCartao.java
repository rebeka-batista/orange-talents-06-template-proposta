package br.com.proposta.bloqueiodocartao;

import br.com.proposta.novaproposta.Proposta;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Entity
public class BloqueioCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBloqueioCartao;

    private LocalDateTime instante;

    private String ipCliente;

    private String userAgent;

    @OneToOne
    private Proposta proposta;

    public BloqueioCartao() {
    }

    public BloqueioCartao(LocalDateTime instante, HttpServletRequest request) {
        this.instante = instante;
        this.ipCliente = request.getRemoteAddr();
        this.userAgent = request.getHeader("User-Agent");
    }

    public BloqueioCartao(Proposta proposta, LocalDateTime instante, String ipClient, String userAgent) {
        this.proposta = proposta;
        this.instante = instante;
        this.ipCliente = ipClient;
        this.userAgent = userAgent;
    }

    public Long getIdBloqueioCartao() {
        return idBloqueioCartao;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Proposta getProposta() {
        return proposta;
    }
}
