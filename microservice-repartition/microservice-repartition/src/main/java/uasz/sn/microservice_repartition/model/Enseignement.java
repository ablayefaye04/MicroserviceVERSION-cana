package uasz.sn.microservice_repartition.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Enseignement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String formation;
    private int niveau;
    private int semestre;
    private String nom;

    @ManyToMany(mappedBy = "enseignements", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference // Côté maître
    @JsonIgnoreProperties("enseignement") // Ignore la propriété "enseignement" dans Repartition

    private List<Enseignant> enseignants = new ArrayList<>();

    public Enseignement() {
    }

    public Enseignement(Long id, String formation, int niveau, int semestre, String nom, List<Enseignant> enseignants) {
        this.id = id;
        this.formation = formation;
        this.niveau = niveau;
        this.semestre = semestre;
        this.nom = nom;
        this.enseignants = enseignants;
    }

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

    public List<Enseignant> getEnseignants() {
        return enseignants;
    }

    public void setEnseignants(List<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }
}
