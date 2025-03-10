package sn.uasz.EmploisDuTempsBackend.Modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ECLocal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle, code, description;
    private int cm, tp, td, hTotal, tpe, vht, coefficient;
//    @ToString.Exclude
//    @ManyToOne
    private long ueId;
//    @ManyToOne
//    @JoinColumn(name = "enseignement_id") // Spécifiez la clé étrangère
    private long enseignementId;

    private int credit;

    public Long getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

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

    public void setCm(int cm) {
        this.cm = cm;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }

    public void setTd(int td) {
        this.td = td;
    }

    public void sethTotal(int hTotal) {
        this.hTotal = hTotal;
    }

    public void setTpe(int tpe) {
        this.tpe = tpe;
    }

    public void setVht(int vht) {
        this.vht = vht;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public void setUeId(long ueId) {
        this.ueId = ueId;
    }

    public void setEnseignementId(long enseignementId) {
        this.enseignementId = enseignementId;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}