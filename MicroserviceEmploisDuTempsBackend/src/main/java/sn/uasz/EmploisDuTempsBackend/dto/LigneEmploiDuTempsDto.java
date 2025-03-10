package sn.uasz.EmploisDuTempsBackend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
@Data
@Getter
@Setter
public class LigneEmploiDuTempsDto {
    private Long id;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private String jour;
    private String salle;
    private String enseignementLocal;

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getSalle() {
        return salle;
    }

    public String getEnseignementLocal() {
        return enseignementLocal;
    }

    public void setEnseignementLocal(String enseignementLocal) {
        this.enseignementLocal = enseignementLocal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }



    public Long getId() {
        return id;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public String getJour() {
        return jour;
    }

}

