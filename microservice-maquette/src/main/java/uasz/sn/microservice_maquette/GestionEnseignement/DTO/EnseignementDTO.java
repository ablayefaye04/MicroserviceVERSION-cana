package uasz.sn.microservice_maquette.GestionEnseignement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class EnseignementDTO {
    private Long id;
    private String formation;
    private int niveau;
    private int semestre;
    private String nom;

    // Constructeur par défaut
    public EnseignementDTO() {
    }

    // Constructeur avec tous les champs
    public EnseignementDTO(Long id, String formation, int niveau, int semestre, String nom) {
        this.id = id;
        this.formation = formation;
        this.niveau = niveau;
        this.semestre = semestre;
        this.nom = nom;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Méthode toString (optionnelle)
    @Override
    public String toString() {
        return "EnseignementDTO{" +
                "id=" + id +
                ", formation='" + formation + '\'' +
                ", niveau=" + niveau +
                ", semestre=" + semestre +
                ", nom='" + nom + '\'' +
                '}';
    }
}