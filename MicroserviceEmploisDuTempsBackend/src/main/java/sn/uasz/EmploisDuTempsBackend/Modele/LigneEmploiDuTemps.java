package sn.uasz.EmploisDuTempsBackend.Modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class LigneEmploiDuTemps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private String jour;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "salle_id", nullable = true)
    @JsonManagedReference
    private Salle salle;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    private EnseignementLocal enseignementLocal;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idEmploi")
    private EmploiDuTemps emploiDuTemps;

    public EnseignementLocal getEnseignementLocal() {
        return enseignementLocal;
    }

    public Long getId() {
        return id;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public String getJour() {
        return jour;
    }

    public Salle getSalle() {
        return salle;
    }

    public EmploiDuTemps getEmploiDuTemps() {
        return emploiDuTemps;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public void setEnseignementLocal(EnseignementLocal enseignementLocal) {
        this.enseignementLocal = enseignementLocal;
    }

    public void setEmploiDuTemps(EmploiDuTemps emploiDuTemps) {
        this.emploiDuTemps = emploiDuTemps;
    }
}
