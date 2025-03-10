package sn.uasz.EmploisDuTempsBackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.EmploisDuTempsBackend.Modele.ECLocal;

import java.util.Optional;

public interface ECRepository extends JpaRepository<ECLocal, Long> {

    Optional<ECLocal> findById(Long id);
}