package uasz.sn.microservice_maquette.GestionDesUE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.microservice_maquette.GestionDesEC.repository.ECRepository;
import uasz.sn.microservice_maquette.GestionDesEC.service.ECService;
import uasz.sn.microservice_maquette.GestionDesUE.model.UE;
import uasz.sn.microservice_maquette.GestionDesUE.repository.UERepository;

import java.util.Arrays;
import java.util.List;

@Service
public class UEService {
    @Autowired
    private UERepository ueRepository;
    @Autowired
    private ECService ecService;
    @Autowired
    private ECRepository ecRepository;
    /*@Autowired
    private MaquetteRepository maquetteRepository;
    @Autowired
    private EnseignementRepository enseignementRepository;
*/
    public UE create(UE ue){
        return ueRepository.save(ue);
    }
    public UE update(UE ue) {
        UE ueExisting = ueRepository.findById(ue.getId()).orElse(null); // Utilisation de orElse pour éviter les exceptions
        if (ueExisting == null) {
            return null; // Entité non trouvée
        } else {
            return ueRepository.save(ue); // Mise à jour de l'UE
        }
    }

    public void delete(Long id) {
        UE ue = ueRepository.findById(id).orElse(null);
        ueRepository.delete(ue);
    }
    public List<UE> findAll(){
        return ueRepository.findAll();
    }

    public UE findById(Long id) {
        return ueRepository.findById(id).orElse(null); // Retourne null si non trouvé
    }

    public UE findByIntitule(String intitule){
        return ueRepository.findByIntitule(intitule);
    }




    public void activer(Long id){
        UE ue = ueRepository.findById(id).get();
        if(ue != null){
            if(ue.isActive()){
                ue.setActive(false);
            }else{
                ue.setActive(true);
            }
            ueRepository.save(ue);
        }
    }
    public List<UE> findByIds(Long[] ids) {
        return ueRepository.findAllById(Arrays.asList(ids));
    }

    public void archiver(Long id){
        UE ue = ueRepository.findById(id).get();
        if(ue != null){
            if(ue.isArchive()){
                ue.setArchive(false);
            }else{
                ue.setArchive(true);
            }
        }
    }

}
