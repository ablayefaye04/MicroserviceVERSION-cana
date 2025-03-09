package uasz.sn.microservice_repartition.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity @Data
public class Repartition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private LocalDate dateChoix;

    @ManyToOne
    @JoinColumn(name = "enseignement_id")
    private Enseignement enseignement;

    @ManyToOne
    @JoinColumn(name = "enseignant_id")
    private Enseignant enseignant;

    private boolean valide;

    public Repartition() {
    }

    public Repartition(Long id, String type, LocalDate dateChoix, Enseignement enseignement, Enseignant enseignant, boolean valide) {
        this.id = id;
        this.type = type;
        this.dateChoix = dateChoix;
        this.enseignement = enseignement;
        this.enseignant = enseignant;
        this.valide = valide;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDateChoix() {
        return dateChoix;
    }

    public void setDateChoix(LocalDate dateChoix) {
        this.dateChoix = dateChoix;
    }

    public Enseignement getEnseignement() {
        return enseignement;
    }

    public void setEnseignement(Enseignement enseignement) {
        this.enseignement = enseignement;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }
}
