package uasz.sn.microservice_repartition.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Enseignant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String prenom;
    private String nom;

    @ManyToMany
    @JoinTable(
            name = "repartition",
            joinColumns = @JoinColumn(name = "enseignant_id"),
            inverseJoinColumns = @JoinColumn(name = "enseignement_id")
    )
    @JsonBackReference // Côté esclave
    @JsonIgnoreProperties("enseignement") // Ignore la propriété "enseignement" dans Repartition

    private List<Enseignement> enseignements = new ArrayList<>();

    public Enseignant() {
    }

    public Enseignant(Long id, String prenom, String nom, List<Enseignement> enseignements) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.enseignements = enseignements;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Enseignement> getEnseignements() {
        return enseignements;
    }

    public void setEnseignements(List<Enseignement> enseignements) {
        this.enseignements = enseignements;
    }
}
