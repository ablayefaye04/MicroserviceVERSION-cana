package uasz.sn.microservice_maquette.GestionMaquette.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uasz.sn.microservice_maquette.GestionMaquette.model.Maquette;

@RepositoryRestResource
public interface MaquetteRepository extends JpaRepository<Maquette,Long> {
    @Query("SELECT m FROM Maquette m WHERE m.nom = :nom AND m.semestre = :semestre")
    public Maquette findByNomAndSemestre(@Param("nom") String nom,@Param("semestre") int semestre);
}
