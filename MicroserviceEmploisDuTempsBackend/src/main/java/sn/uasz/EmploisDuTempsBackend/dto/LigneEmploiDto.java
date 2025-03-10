package sn.uasz.EmploisDuTempsBackend.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class LigneEmploiDto {

    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Long salleId;
    private Long enseignementId;
    private String jour;

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public Long getSalleId() {
        return salleId;
    }

    public Long getEnseignementId() {
        return enseignementId;
    }

    public String getJour() {
        return jour;
    }
}
