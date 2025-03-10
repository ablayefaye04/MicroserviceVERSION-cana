package sn.uasz.EmploisDuTempsBackend.Utilisateur.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.uasz.EmploisDuTempsBackend.Authentification.model.Utilisateur;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Enseignant extends Utilisateur {
    private String specialite;
    private boolean archive;
}