package br.com.proposta.bloqueiodocartao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SistemaBloqueioRequest {

    @JsonProperty("sistemaResponsavel")
    private String sistemaResponsavel;

    public SistemaBloqueioRequest() {
    }

    public SistemaBloqueioRequest(String sistemsResponsavel) {
        this.sistemaResponsavel = sistemsResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
