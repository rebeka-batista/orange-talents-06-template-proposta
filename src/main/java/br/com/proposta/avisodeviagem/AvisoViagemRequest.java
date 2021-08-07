package br.com.proposta.avisodeviagem;

import br.com.proposta.novaproposta.Proposta;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemRequest {

    @NotNull
    @JsonProperty("instanteViagem")
    private LocalDate instanteViagem;

    @NotBlank
    @JsonProperty("ipClient")
    private String ipClient;

    @NotBlank
    @JsonProperty("userAgent")
    private String userAgent;

    @NotBlank
    @JsonProperty("destinoViagem")
    private String destinoViagem;

    @NotNull
    @Future
    @JsonProperty("terminoViagem")
    private LocalDate terminoViagem;


    public AvisoViagemRequest() {
    }

    public AvisoViagemRequest(LocalDate instanteViagem, String ipClient,
                              String userAgent, String destinoViagem, LocalDate terminoViagem) {
        this.instanteViagem = instanteViagem;
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.destinoViagem = destinoViagem;
        this.terminoViagem = terminoViagem;
    }

    public AvisoViagem converter(Proposta proposta, HttpServletRequest request) {
        return new AvisoViagem(LocalDate.now(), request.getRemoteAddr(), request.getHeader("User-Agent"),
                destinoViagem, terminoViagem, proposta);
    }

    public LocalDate getInstanteViagem() {
        return instanteViagem;
    }

    public String getIpClient() {
        return ipClient;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getDestinoViagem() {
        return destinoViagem;
    }

    public LocalDate getTerminoViagem() {
        return terminoViagem;
    }
}
