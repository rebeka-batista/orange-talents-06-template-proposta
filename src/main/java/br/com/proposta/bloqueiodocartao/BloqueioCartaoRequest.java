package br.com.proposta.bloqueiodocartao;

import br.com.proposta.novaproposta.Proposta;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class BloqueioCartaoRequest {

    @JsonIgnore
    private LocalDateTime instante = LocalDateTime.now();

    @JsonIgnore
    private Long idBloqueio;

    @JsonIgnore
    private String ipClient;

    @JsonIgnore
    private String userAgent;

    @JsonIgnore
    private Long idProposta;

    private HttpServletRequest request;

    @PersistenceContext
    private EntityManager manager;


    public BloqueioCartaoRequest() {
    }

    public BloqueioCartaoRequest(Long idProposta, Long idBloqueio, LocalDateTime instante,
                                 String ipClient, String userAgent, HttpServletRequest request) {
        this.idProposta = idProposta;
        this.idBloqueio = idBloqueio;
        this.instante = LocalDateTime.now();
        this.ipClient = request.getRemoteAddr();
        this.userAgent = request.getHeader("User-Agent");
    }

    public BloqueioCartao converter(Proposta proposta, HttpServletRequest request) {
        return new BloqueioCartao(proposta, instante, request.getRemoteAddr(), request.getHeader("User-Agent"));
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public Long getIdBloqueio() {
        return idBloqueio;
    }

    public String getIpClient() {
        return ipClient;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public EntityManager getManager() {
        return manager;
    }
}
