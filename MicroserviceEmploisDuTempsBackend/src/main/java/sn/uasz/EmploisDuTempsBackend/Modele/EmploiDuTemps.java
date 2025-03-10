package sn.uasz.EmploisDuTempsBackend.Modele;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmploiDuTemps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    @OneToMany(mappedBy = "emploiDuTemps", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<LigneEmploiDuTemps> ligneEmploiDuTemps = new ArrayList<>();

    public List<LigneEmploiDuTemps> getLigneEmploiDuTemps() {
        return ligneEmploiDuTemps;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public void setLigneEmploiDuTemps(List<LigneEmploiDuTemps> ligneEmploiDuTemps) {
        this.ligneEmploiDuTemps = ligneEmploiDuTemps;
    }
}
