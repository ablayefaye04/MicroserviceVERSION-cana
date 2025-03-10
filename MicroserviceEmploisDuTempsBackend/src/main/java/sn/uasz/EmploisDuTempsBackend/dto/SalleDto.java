package sn.uasz.EmploisDuTempsBackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import sn.uasz.EmploisDuTempsBackend.Modele.LigneEmploiDuTemps;

@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalleDto {
    private Long id;
    private String code;
    private String libelle;
    private int numero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ligne_emploi_du_temps_id")
    private LigneEmploiDuTemps ligneEmploiDuTemps;

    public void setLigneEmploiDuTemps(LigneEmploiDuTemps ligneEmploiDuTemps) {
        this.ligneEmploiDuTemps = ligneEmploiDuTemps;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}