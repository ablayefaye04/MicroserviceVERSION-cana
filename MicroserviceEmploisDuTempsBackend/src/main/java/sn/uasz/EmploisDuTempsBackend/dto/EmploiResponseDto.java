package sn.uasz.EmploisDuTempsBackend.dto;

import lombok.*;
import sn.uasz.EmploisDuTempsBackend.Modele.EmploiDuTemps;
import sn.uasz.EmploisDuTempsBackend.Modele.EnseignementLocal;
import sn.uasz.EmploisDuTempsBackend.Modele.Salle;

import java.util.List;

@NoArgsConstructor
@Data
@Getter
@Setter
public class EmploiResponseDto {
    private List<EnseignementLocal> enseignements;
    private List<EmploiDuTemps> emploiDuTemps;
    private List<Salle> salles;
    private String nomUtilisateur;
    private String prenomUtilisateur;
    private String message; // Ajouter un champ pour le message d'erreur

    public EmploiResponseDto(List<EnseignementLocal> enseignements, List<EmploiDuTemps> emploiDuTemps, List<Salle> salles, String nomUtilisateur, String prenomUtilisateur) {
        this.enseignements = enseignements;
        this.emploiDuTemps = emploiDuTemps;
        this.salles = salles;
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
    }

    // Ajouter un constructeur prenant uniquement un message d'erreur
    public EmploiResponseDto(String message) {
        this.message = message;
    }
}
