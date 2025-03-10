package sn.uasz.EmploisDuTempsBackend.Modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle, code, description;
    private int credit, coefficient;


    @OneToMany(mappedBy = "ueId", fetch = FetchType.EAGER)
    private List<ECLocal> ecLocals;

    private String semestre;
    private  int totalCredit;
    private  int totalCoefficient;

//    @ToString.Exclude
//    @ManyToOne
    private Long idMaquette;

    private int nbrEC;
    private int totalCm;
    private int totalTd;
    private int totalTp;
    private int totalTpe;
    private int totalVht;
    private int totalCoeff;


    @ManyToOne
    @JoinColumn(name = "enseignement_id") // Spécifiez la clé étrangère
    private EnseignementLocal enseignementLocal;

    public void setId(Long id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public void setEcLocals(List<ECLocal> ecLocals) {
        this.ecLocals = ecLocals;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public void setTotalCredit(int totalCredit) {
        this.totalCredit = totalCredit;
    }

    public void setTotalCoefficient(int totalCoefficient) {
        this.totalCoefficient = totalCoefficient;
    }

    public void setIdMaquette(Long idMaquette) {
        this.idMaquette = idMaquette;
    }

    public void setNbrEC(int nbrEC) {
        this.nbrEC = nbrEC;
    }

    public void setTotalCm(int totalCm) {
        this.totalCm = totalCm;
    }

    public void setTotalTd(int totalTd) {
        this.totalTd = totalTd;
    }

    public void setTotalTp(int totalTp) {
        this.totalTp = totalTp;
    }

    public void setTotalTpe(int totalTpe) {
        this.totalTpe = totalTpe;
    }

    public void setTotalVht(int totalVht) {
        this.totalVht = totalVht;
    }

    public void setTotalCoeff(int totalCoeff) {
        this.totalCoeff = totalCoeff;
    }

    public void setEnseignementLocal(EnseignementLocal enseignementLocal) {
        this.enseignementLocal = enseignementLocal;
    }
}