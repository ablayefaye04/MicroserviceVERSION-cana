package sn.uasz.EmploisDuTempsBackend.Modele;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String code;
    private String niveau;
    private int effectif;
    private int nbrGroupe;
    private long formationId;
    private String libelle;

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getNiveau() {
        return niveau;
    }

    public int getEffectif() {
        return effectif;
    }

    public int getNbrGroupe() {
        return nbrGroupe;
    }

    public long getFormationId() {
        return formationId;
    }

    public String getLibelle() {
        return libelle;
    }
}
