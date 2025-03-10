package sn.uasz.EmploisDuTempsBackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sn.uasz.EmploisDuTempsBackend.Modele.EnseignementLocal;

import java.util.List;

public interface EnseignementLocalRepository extends JpaRepository<EnseignementLocal, Long> {
    @Query("SELECT e FROM EnseignementLocal e LEFT JOIN LigneEmploiDuTemps l ON e.id = l.enseignementLocal.id WHERE l.id IS NULL")
    List<EnseignementLocal> findEnseignementsNonUtilises();
}
