package uasz.sn.microservice_maquette.GestionEnseignement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.microservice_maquette.GestionEnseignement.DTO.EnseignementDTO;
import uasz.sn.microservice_maquette.GestionEnseignement.model.Enseignement;
import uasz.sn.microservice_maquette.GestionEnseignement.repository.EnseignementRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnseignementService {
    @Autowired
    private EnseignementRepository enseignementRepository;

    public Enseignement create(Enseignement enseignement){
        return enseignementRepository.save(enseignement);
    }

    public EnseignementDTO getEnseignementDTO(Long id) {
        Optional<Enseignement> enseignementOpt = enseignementRepository.findById(id);

        if (enseignementOpt.isEmpty()) {
            return null; // Retourne null si l'enseignement n'existe pas
        }

        Enseignement enseignement = enseignementOpt.get();
        EnseignementDTO enseignementDTO = new EnseignementDTO();
        enseignementDTO.setId(enseignement.getId());
        enseignementDTO.setNom(enseignement.getEc().getIntitule()
                );
        enseignementDTO.setFormation(enseignement.getMaquette().getClasse().getFormation().getIntitule());
        enseignementDTO.setSemestre(enseignement.getMaquette().getSemestre());
        enseignementDTO.setNiveau(enseignement.getMaquette().getClasse().getNiveau());

        return enseignementDTO;  // ✅ On retourne bien l'objet
    }

    public List<EnseignementDTO> getEnseignementsDTO(){
        List<Enseignement> enseignements = enseignementRepository.findAll();
        List<EnseignementDTO> enseignementDTOS = new ArrayList<>();

        for (Enseignement e : enseignements) {
            EnseignementDTO enseignementDTO = getEnseignementDTO(e.getId());
            if (enseignementDTO != null) {
                enseignementDTOS.add(enseignementDTO);
            } else {
                System.out.println("EnseignementDTO null pour l'ID : " + e.getId());
            }
        }

        return enseignementDTOS;
    }

    public boolean Exist(Long ec, Long maquette){
        if(enseignementRepository.findByEcIdAndMaquetteId(ec,maquette) != null){
            return true;
        }else {
            return false;
        }
    }
    public Enseignement update(Enseignement enseignement){
        Enseignement existing = enseignementRepository.findById(enseignement.getId()).get();
       return (enseignement == null ? null : enseignementRepository.save(enseignement));
    }

    public void delete(Enseignement enseignement){
        enseignementRepository.delete(enseignement);
    }

    public List<Enseignement> findAll(){
        return enseignementRepository.findAll();
    }

    public   Enseignement findById(Long id){
        return enseignementRepository.findById(id).get();
    }

    public Enseignement findByEcAndMaquette(Long ec,Long maquette){
        return enseignementRepository.findByEcIdAndMaquetteId(ec,maquette);
    }

    public List<Enseignement> findByMaquette(Long maquette){
        return enseignementRepository.findByMaquetteId(maquette);
    }

    public List<Enseignement> findByEc(Long ec){
        return enseignementRepository.findByEcId(ec);
    }
}
