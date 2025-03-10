package sn.uasz.EmploisDuTempsBackend.Utilisateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.uasz.EmploisDuTempsBackend.Modele.EmploiDuTemps;

import java.util.List;

@Repository
public interface EmploiDuTempsRepository extends JpaRepository<EmploiDuTemps,Long> {
    @Query("SELECT e FROM EmploiDuTemps e JOIN e.ligneEmploiDuTemps l WHERE l.enseignementLocal.choixLocal.enseignant.id = :enseignantId")
    List<EmploiDuTemps> findByEnseignantId(@Param("enseignantId") long enseignantId);

}
