package sn.uasz.EmploisDuTempsBackend.Modele;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private Integer numero;
    private String libelle;

    @OneToMany(mappedBy = "salle", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<LigneEmploiDuTemps> lignesEmploiDuTemps = new ArrayList<>();

    public List<LigneEmploiDuTemps> getLignesEmploiDuTemps() {
        return lignesEmploiDuTemps;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setLignesEmploiDuTemps(List<LigneEmploiDuTemps> lignesEmploiDuTemps) {
        this.lignesEmploiDuTemps = lignesEmploiDuTemps;
    }


    public Integer getNumero() {
        return numero;
    }

    public String getLibelle() {
        return libelle;
    }
}