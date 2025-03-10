package sn.uasz.EmploisDuTempsBackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sn.uasz.EmploisDuTempsBackend.Modele.Salle;

import java.util.List;

@Repository
public interface SalleRepository extends JpaRepository<Salle, Long> {
    @Query("SELECT s FROM Salle s WHERE s.id NOT IN (SELECT l.salle.id FROM LigneEmploiDuTemps l)")
    List<Salle> findSallesNonUtilises();
}
