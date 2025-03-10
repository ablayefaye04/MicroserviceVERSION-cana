package sn.uasz.EmploisDuTempsBackend.Modele;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class EnseignementLocal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private ChoixLocal choixLocal;

    private boolean valide; // Indique si le choix a été validé

    @OneToMany(mappedBy = "enseignementLocal", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<LigneEmploiDuTemps> lignesEmploiDuTemps = new ArrayList<>();

    public ChoixLocal getChoixLocal() {
        return choixLocal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setChoixLocal(ChoixLocal choixLocal) {
        this.choixLocal = choixLocal;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }
}

