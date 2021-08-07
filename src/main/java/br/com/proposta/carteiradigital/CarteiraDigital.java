package br.com.proposta.carteiradigital;

import br.com.proposta.novaproposta.Proposta;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class CarteiraDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCarteira;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private AssociaCarteiraDigital associaCarteiraDigital;

    @ManyToOne
    private Proposta proposta;

//    public CarteiraDigital(@NotBlank @Email String email, AssociaCarteiraDigital associaCarteiraDigital, Proposta proposta) {
//    }

//    public CarteiraDigital(String email, AssociaCarteiraDigital associaCarteiraDigital, Proposta proposta) {
//        this.email = email;
//        this.associaCarteiraDigital = associaCarteiraDigital;
//        this.proposta = proposta;
//    }

    public CarteiraDigital(String email, AssociaCarteiraDigital associaCarteiraDigital, Proposta proposta) {
        this.idCarteira = idCarteira;
        this.email = email;
        this.associaCarteiraDigital = associaCarteiraDigital;
        this.proposta = proposta;
    }



    public Long getIdCarteira() {
        return idCarteira;
    }

    public void setIdCarteira(Long idCarteira) {
        this.idCarteira = idCarteira;
    }

    public String getEmail() {
        return email;
    }

    public AssociaCarteiraDigital getAssociaCarteiraDigital() {
        return associaCarteiraDigital;
    }

    public Proposta getProposta() {
        return proposta;
    }

}
