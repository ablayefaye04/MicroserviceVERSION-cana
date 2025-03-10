package sn.uasz.EmploisDuTempsBackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.EmploisDuTempsBackend.Modele.Classe;

public interface ClasseRepository extends JpaRepository<Classe, Long> {
    Classe findByLibelle(String libelle);
}
