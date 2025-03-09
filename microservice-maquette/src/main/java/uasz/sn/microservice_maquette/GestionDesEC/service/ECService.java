package uasz.sn.microservice_maquette.GestionDesEC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.microservice_maquette.GestionDesEC.model.EC;
import uasz.sn.microservice_maquette.GestionDesEC.repository.ECRepository;
import uasz.sn.microservice_maquette.GestionDesUE.model.UE;

import java.util.Arrays;
import java.util.List;

@Service
public class ECService {
    @Autowired
    private ECRepository ecRepository;
    /*@Autowired
    private EnseignementService enseignementService;
*/
    public EC create(EC ec){
        return ecRepository.save(ec);
    }

    public EC update(EC ec){
        EC ecExisting = ecRepository.findById(ec.getId()).get();
        if(ecExisting == null){
            return null;
        }else{
            return ecRepository.save(ec);
        }
    }


    public List<EC> findByUEId(Long ueId) {
        return ecRepository.findByUeId(ueId); // Appel de la méthode corrigée
    }
    public void delete(EC ec){
        ecRepository.delete(ec);
    }

    public List<EC> findAll(){
        return ecRepository.findAll();
    }
    public EC findByIntitule(String intitule){
        return ecRepository.findByIntitule(intitule);
    }

    public  EC findById(Long id){
        return ecRepository.findById(id).get();
    }
    public EC findByCode(String code){
        return ecRepository.findByCode(code);
    }

    public void activer(Long id){
        EC ec = ecRepository.findById(id).get();
        if(ec!= null){
            if(ec.isActive()){
                ec.setActive(false);
            }else{
                ec.setActive(true);
            }
            ecRepository.save(ec);
        }
    }

    public List<EC> findByIds(Long[] ids) {
        return ecRepository.findAllById(Arrays.asList(ids));
    }

    public void archiver(Long id){
        EC ec = ecRepository.findById(id).get();
        if(ec != null){
            if(ec.isArchive()){
                ec.setArchive(false);
            }else{
                ec.setArchive(true);
            }
        }
    }
}
