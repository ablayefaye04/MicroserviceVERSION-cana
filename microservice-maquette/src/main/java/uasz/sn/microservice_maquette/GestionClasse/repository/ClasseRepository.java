package uasz.sn.microservice_maquette.GestionClasse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uasz.sn.microservice_maquette.GestionClasse.model.Classe;

import java.util.List;

@RepositoryRestResource
public interface ClasseRepository extends JpaRepository<Classe,Long> {

    @Query("SELECT c FROM Classe c WHERE c.formation.id = :idFormation")
    List<Classe> findByFormationId(@Param("idFormation") Long idFormation);

}
