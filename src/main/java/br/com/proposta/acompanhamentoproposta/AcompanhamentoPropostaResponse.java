package br.com.proposta.acompanhamentoproposta;

import br.com.proposta.consultadadossolicitante.RetornoStatus;
import br.com.proposta.novaproposta.Proposta;

import java.math.BigDecimal;

public class AcompanhamentoPropostaResponse {

    private Long idProposta;
    private String nome;
    private String email;
    private String documento;
    private String endereco;
    private BigDecimal salario;
    private RetornoStatus retornoStatus;
    private String cartao;


    public AcompanhamentoPropostaResponse() {
    }

    public AcompanhamentoPropostaResponse(Proposta proposta) {
        this.idProposta = proposta.getIdProposta();
        this.nome = proposta.getNome();
        this.email = proposta.getEmail();
        this.documento = proposta.getDocumento();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.retornoStatus = proposta.getStatusProposta();
        this.cartao = proposta.getCartao();
    }


}
