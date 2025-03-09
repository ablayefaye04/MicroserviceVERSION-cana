package uasz.sn.microservice_maquette.GestionFormation.service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.microservice_maquette.GestionFormation.model.Formation;
import uasz.sn.microservice_maquette.GestionFormation.repository.FormationRepository;

import java.util.List;

@Service
@Transactional
public class FormationService {
    @Autowired
    private FormationRepository formationRepository;

    public Formation create(Formation formation){
        Formation formation1 = formationRepository.findByIntitule(formation.getIntitule());
        if(formation1 != null){
            return null;
        }
        return formationRepository.save(formation);
    }

    public Formation update(Formation formation){
        Formation formation1 = formationRepository.findById(formation.getId()).get();
        if(formation1 == null){
            return null;
        }
        return formationRepository.save(formation);

    }

    public void delete(Formation formation){
        Formation formation1 = formationRepository.findById(formation.getId()).get();
        if(formation1 != null){
            formationRepository.delete(formation);
        }
    }

    public List<Formation> findAll(){
        return formationRepository.findAll();
    }

    public Formation findByIntitule(String intitule){
        return formationRepository.findByIntitule(intitule);
    }
    public Formation findById(Long id){return formationRepository.findById(id).get();}

    public void archiver(Long id){
        Formation formation = formationRepository.findById(id).get();
        if(formation != null){
            if(formation.isArchive()){
                formation.setArchive(false);
            }else{
                formation.setArchive(true);
            }
        }
    }
}
