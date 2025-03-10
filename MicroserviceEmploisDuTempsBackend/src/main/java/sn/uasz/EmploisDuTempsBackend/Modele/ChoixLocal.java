package sn.uasz.EmploisDuTempsBackend.Modele;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.uasz.EmploisDuTempsBackend.Utilisateur.model.Enseignant;

import java.time.LocalDate;
;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChoixLocal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // CM, TD, TP

   @ManyToOne
    @JoinColumn(name = "enseignant_id", nullable = false)
    private Enseignant enseignant;

//    @ManyToOne
//    @JoinColumn(name = "ec_id", nullable = false)
    private long ecId; // L’EC choisi

    private LocalDate date;
//    @ManyToOne
    private long formationId;
//    @ManyToOne
    private long classeId;


    private boolean accepteParChef; // Pour indiquer si le chef a validé ce choix

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public long getEcId() {
        return ecId;
    }

    public LocalDate getDate() {
        return date;
    }

    public long getFormationId() {
        return formationId;
    }

    public long getClasseId() {
        return classeId;
    }

    public boolean isAccepteParChef() {
        return accepteParChef;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEnseignant(Enseignant enseignantId) {
        this.enseignant = enseignantId;
    }

    public void setEcId(long ecId) {
        this.ecId = ecId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setFormationId(long formationId) {
        this.formationId = formationId;
    }

    public void setClasseId(long classeId) {
        this.classeId = classeId;
    }

    public void setAccepteParChef(boolean accepteParChef) {
        this.accepteParChef = accepteParChef;
    }
}

