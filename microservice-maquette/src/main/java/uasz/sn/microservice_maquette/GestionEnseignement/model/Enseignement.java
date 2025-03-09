package uasz.sn.microservice_maquette.GestionEnseignement.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.microservice_maquette.GestionDesEC.model.EC;
import uasz.sn.microservice_maquette.GestionMaquette.model.Maquette;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"maquette_id", "ec_id"}))
public class Enseignement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnoreProperties("enseignements") // Évite la boucle infinie avec EC

    @ManyToOne
    @JoinColumn(name = "ec_id", nullable = false)
    private EC ec;

    @ManyToOne
    @JoinColumn(name = "maquette_id", nullable = false)
    private Maquette maquette;

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EC getEc() {
        return ec;
    }

    public void setEc(EC ec) {
        this.ec = ec;
    }

    public Maquette getMaquette() {
        return maquette;
    }

    public void setMaquette(Maquette maquette) {
        this.maquette = maquette;
    }
}