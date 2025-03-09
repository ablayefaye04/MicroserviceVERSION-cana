package uasz.sn.microservice_maquette.GestionEnseignement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uasz.sn.microservice_maquette.GestionEnseignement.model.Enseignement;

import java.util.List;

@RepositoryRestResource
public interface EnseignementRepository extends JpaRepository<Enseignement,Long> {
    public List<Enseignement> findByEcId(Long ecId);
    public List<Enseignement> findByMaquetteId(Long maquetteId);
    public Enseignement findByEcIdAndMaquetteId(Long ecId,Long maquetteId);
}
