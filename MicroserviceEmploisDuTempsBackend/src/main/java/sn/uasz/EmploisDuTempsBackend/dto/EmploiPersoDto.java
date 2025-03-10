package sn.uasz.EmploisDuTempsBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmploiPersoDto {

    private String type;
    private String ec;
    private String Salle;
    private String jour;
    private LocalTime debut;
    private LocalTime fin;
    private String nom;
    private String prenom;

    public void setType(String type) {
        this.type = type;
    }

    public void setEc(String ec) {
        this.ec = ec;
    }

    public void setSalle(String salle) {
        Salle = salle;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public void setDebut(LocalTime debut) {
        this.debut = debut;
    }

    public void setFin(LocalTime fin) {
        this.fin = fin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getType() {
        return type;
    }

    public String getEc() {
        return ec;
    }

    public String getSalle() {
        return Salle;
    }

    public String getJour() {
        return jour;
    }

    public LocalTime getDebut() {
        return debut;
    }

    public LocalTime getFin() {
        return fin;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
}
