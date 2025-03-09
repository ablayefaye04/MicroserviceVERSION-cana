package uasz.sn.microservice_repartition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uasz.sn.microservice_repartition.model.Enseignant;
import uasz.sn.microservice_repartition.model.Enseignement;
import uasz.sn.microservice_repartition.model.Repartition;


import java.util.List;

@RepositoryRestResource
public interface RepartitionRepository extends JpaRepository<Repartition,Long> {
    public List<Repartition> findByEnseignant(Enseignant enseignant);
    public Repartition findByEnseignantAndEnseignementAndType(Enseignant enseignant, Enseignement enseignement, String type);
    public Repartition findByEnseignement(Enseignement enseignement);
    public List<Repartition> findByEnseignementAndType(Enseignement enseignement, String type);
}
