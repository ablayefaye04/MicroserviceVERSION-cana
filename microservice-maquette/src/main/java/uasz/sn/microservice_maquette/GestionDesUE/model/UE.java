package uasz.sn.microservice_maquette.GestionDesUE.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.microservice_maquette.GestionDesEC.model.EC;
import uasz.sn.microservice_maquette.GestionMaquette.model.Maquette;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class UE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(unique = true, nullable = false)
    private String intitule;

    private int credit;
    private int coefficient;

    private boolean active;
    private boolean archive;

    @OneToMany(mappedBy = "ue", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EC> ecList = new ArrayList<>();

    @JsonBackReference
    @JsonIgnoreProperties("ueList") // Ignore la propriété "ueList" dans Maquette pour éviter la boucle infinie
    @ManyToMany(mappedBy = "ueList")
    private List<Maquette> maquettes = new ArrayList<>();

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public List<EC> getEcList() {
        return ecList;
    }

    public void setEcList(List<EC> ecList) {
        this.ecList = ecList;
    }

    public List<Maquette> getMaquettes() {
        return maquettes;
    }

    public void setMaquettes(List<Maquette> maquettes) {
        this.maquettes = maquettes;
    }
}