package uasz.sn.microservice_maquette.GestionDesEC.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uasz.sn.microservice_maquette.GestionDesEC.model.EC;

import java.util.List;

public interface ECRepository extends JpaRepository<EC,Long> {
    public List<EC> findByUeId(Long id);
    public EC findByIntitule(String intitule);
    public EC findByCode(String code);

}

