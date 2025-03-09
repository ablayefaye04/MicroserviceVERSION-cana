package uasz.sn.microservice_maquette.GestionDesUE.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uasz.sn.microservice_maquette.GestionDesUE.model.UE;

@RepositoryRestResource
public interface UERepository extends JpaRepository<UE,Long> {
    public UE findByIntitule(String intitule);
    public UE findByCode(String code);
}
