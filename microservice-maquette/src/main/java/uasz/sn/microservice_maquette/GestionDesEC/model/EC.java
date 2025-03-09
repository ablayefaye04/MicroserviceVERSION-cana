package uasz.sn.microservice_maquette.GestionDesEC.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.microservice_maquette.GestionDesUE.model.UE;
import uasz.sn.microservice_maquette.GestionEnseignement.model.Enseignement;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class EC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(unique = true, nullable = false)
    private String intitule;

    private int CM;
    private int TD;
    private int TP;
    private int coefficient;

    @ManyToOne
    private UE ue;

    private boolean active;
    private boolean archive;

    @OneToMany(mappedBy = "ec", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enseignement> enseignements;

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public int getCM() {
        return CM;
    }

    public void setCM(int CM) {
        this.CM = CM;
    }

    public int getTD() {
        return TD;
    }

    public void setTD(int TD) {
        this.TD = TD;
    }

    public int getTP() {
        return TP;
    }

    public void setTP(int TP) {
        this.TP = TP;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public UE getUe() {
        return ue;
    }

    public void setUe(UE ue) {
        this.ue = ue;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public List<Enseignement> getEnseignements() {
        return enseignements;
    }

    public void setEnseignements(List<Enseignement> enseignements) {
        this.enseignements = enseignements;
    }
}