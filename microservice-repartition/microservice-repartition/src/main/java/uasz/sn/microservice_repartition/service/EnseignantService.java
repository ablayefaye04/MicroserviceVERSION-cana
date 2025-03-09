package uasz.sn.microservice_repartition.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.microservice_repartition.model.Enseignant;
import uasz.sn.microservice_repartition.repository.EnseignantRepository;

import java.util.List;

@Service
public class EnseignantService {
    /*@Autowired
    private EnseignantClient enseignantClient;
    */@Autowired
    private EnseignantRepository enseignantRepository;

    /*public Enseignant findById(Long id){
        return enseignantClient.getEnseignant(id);
    }*/
    public Enseignant findById(Long id) {
        return enseignantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enseignant non trouvé avec l'ID : " + id));
    }

    /*public List<Enseignant> getAll(){
        return enseignantClient.getEnseignant();
    }*/
    /*public void save(Enseignant enseignant){

        List<String> infos = enseignantClient.getInfo(enseignant.getId());
        enseignant.setNom(infos.get(0));
        enseignant.setPrenom(infos.get(1));
        enseignantRepository.save(enseignant);

    }*/
    public void save(Enseignant enseignant){
        enseignantRepository.save(enseignant);
    }

    public List<Enseignant> findAll() {
        return enseignantRepository.findAll();
    }
}
