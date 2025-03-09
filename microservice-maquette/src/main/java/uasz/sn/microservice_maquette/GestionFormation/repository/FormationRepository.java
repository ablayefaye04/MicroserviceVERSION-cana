package uasz.sn.microservice_maquette.GestionFormation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uasz.sn.microservice_maquette.GestionFormation.model.Formation;

@RepositoryRestResource
public interface FormationRepository extends JpaRepository<Formation,Long> {
    public Formation findByIntitule(String intitule);
}
