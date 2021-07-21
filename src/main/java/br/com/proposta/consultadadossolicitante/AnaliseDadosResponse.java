package br.com.proposta.consultadadossolicitante;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class AnaliseDadosResponse {

    @JsonProperty("id_proposta")
    private Long idProposta;

    @JsonProperty("documento")
    private String documento;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("status_proposta")
    @Enumerated(EnumType.STRING)
    private StatusProposta statusProposta;

    public AnaliseDadosResponse() {
    }

    public AnaliseDadosResponse(Long idProposta, String documento, String nome, StatusProposta statusProposta) {
        this.idProposta = idProposta;
        this.documento = documento;
        this.nome = nome;
        this.statusProposta = statusProposta;
    }

    @Override
    public String toString() {
        return "Analise de Dados: " +
                "\nId da Proposta: " + idProposta +
                "\nDocumento: " + documento +
                "\nNome: " + nome +
                "\nStatus da Proposta=" + statusProposta;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }
}
