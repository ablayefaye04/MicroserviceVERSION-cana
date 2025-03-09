package uasz.sn.microservice_maquette.GestionMaquette.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.microservice_maquette.GestionClasse.model.Classe;
import uasz.sn.microservice_maquette.GestionDesUE.model.UE;
import uasz.sn.microservice_maquette.GestionEnseignement.model.Enseignement;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

@Table(
        name = "maquette",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"classe_id", "semestre"})}
)
public class Maquette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nom;

    private int semestre;
    private boolean archive;
    private boolean active;

    @JsonIgnoreProperties("maquettes") // Ignore la propriété "maquettes" dans UE pour éviter la boucle infinie
    @ManyToMany
    @JoinTable(
            name = "maquette_ue",
            joinColumns = @JoinColumn(name = "maquette_id"),
            inverseJoinColumns = @JoinColumn(name = "ue_id")
    )
    private List<UE> ueList = new ArrayList<>();

    @JsonIgnoreProperties("maquettes") // Ignore la propriété "maquettes" dans Classe pour éviter la boucle infinie
    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe;

    @JsonIgnoreProperties("maquette") // Ignore la propriété "maquette" dans Enseignement pour éviter la boucle infinie
    @OneToMany(mappedBy = "maquette", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enseignement> enseignements = new ArrayList<>();

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<UE> getUeList() {
        return ueList;
    }

    public void setUeList(List<UE> ueList) {
        this.ueList = ueList;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public List<Enseignement> getEnseignements() {
        return enseignements;
    }

    public void setEnseignements(List<Enseignement> enseignements) {
        this.enseignements = enseignements;
    }
}