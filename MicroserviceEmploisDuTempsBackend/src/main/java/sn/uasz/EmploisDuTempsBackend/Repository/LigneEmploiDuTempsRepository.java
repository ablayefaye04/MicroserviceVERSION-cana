package sn.uasz.EmploisDuTempsBackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sn.uasz.EmploisDuTempsBackend.Modele.LigneEmploiDuTemps;
import sn.uasz.EmploisDuTempsBackend.Modele.Salle;

import java.time.LocalTime;
import java.util.List;

public interface LigneEmploiDuTempsRepository extends JpaRepository<LigneEmploiDuTemps,Long> {

    boolean existsBySalleAndJourAndHeureDebutBetween(Salle salle, String jour, LocalTime heureDebut, LocalTime heureFin);

    List<LigneEmploiDuTemps> findByEnseignementLocal_ChoixLocal_EnseignantId(Long enseignantId);

    // Requête pour trouver les lignes d'emploi du temps associées à une classe par son ID
    @Query("SELECT l FROM LigneEmploiDuTemps l " +
            "JOIN FETCH l.salle " +  // Forcer le chargement de la salle
            "JOIN FETCH l.enseignementLocal " +  // Forcer le chargement de l'enseignement local
            "WHERE l.enseignementLocal.choixLocal.classeId = :classeId")
    List<LigneEmploiDuTemps> findByEnseignementLocal_ChoixLocal_ClasseId(@Param("classeId") Long classeId);
}

