package uasz.sn.microservice_repartition.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uasz.sn.microservice_repartition.DTO.EnseignementDTO;
import uasz.sn.microservice_repartition.model.Enseignement;
//import uasz.sn.microservice_repartition.model.EnseignementClient;
import uasz.sn.microservice_repartition.repository.EnseignementRepository;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class EnseignementService {
   // @Autowired
   // private EnseignementClient enseignementClient;
    private static final Logger logger = LoggerFactory.getLogger(EnseignementService.class);

    @Autowired
    private EnseignementRepository enseignementRepository;

  /*  public Enseignement getEsclaveById(Long id){
        EnseignementDTO enseignementDTO = enseignementClient.getEnseignement(id);
        Enseignement enseignement = new Enseignement();
        enseignement.setId(enseignementDTO.getId());
        enseignement.setNom(enseignementDTO.getNom());enseignement.setFormation(enseignementDTO.getFormation());
        enseignement.setNiveau(enseignementDTO.getNiveau());enseignement.setSemestre(enseignementDTO.getSemestre());
        return enseignement;
    }*/
  public Enseignement findById(Long id) {
      return enseignementRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Enseignement non trouvé avec l'ID : " + id));}
    public List<Enseignement> findAll(){
        return enseignementRepository.findAll();
    }
  /*  @Scheduled(fixedRate = 60000)
    public void saveAll() {
        logger.info("Vérification de la disponibilité du microservice...");

        try {
            ResponseEntity<String> response = enseignementClient.isAvailable();
            if (!response.getStatusCode().is2xxSuccessful()) {
                logger.warn("Microservice indisponible. Nouvelle tentative dans 60s.");
                return;
            }
        } catch (Exception e) {
            logger.error("Erreur de connexion au microservice : {}", e.getMessage());
            return;
        }

        logger.info("Microservice disponible. Synchronisation en cours...");

        List<EnseignementDTO> enseignementDTOList = enseignementClient.getAllEnseignements();
        if (enseignementDTOList == null || enseignementDTOList.isEmpty()) {
            logger.warn("Aucun enseignement récupéré. Fin du processus.");
            return;
        }

        List<Long> existingIds = enseignementRepository.findAll().stream()
                .map(Enseignement::getId)
                .toList();

        List<Enseignement> enseignementsToSave = new ArrayList<>();
        List<Enseignement> enseignementsToUpdate = new ArrayList<>();

        for (EnseignementDTO dto : enseignementDTOList) {
            if (!existingIds.contains(dto.getId())) {
                Enseignement enseignement = new Enseignement();
                enseignement.setId(dto.getId());
                enseignement.setNom(dto.getNom());
                enseignement.setSemestre(dto.getSemestre());
                enseignement.setFormation(dto.getFormation());
                enseignement.setNiveau(dto.getNiveau());
                enseignementsToSave.add(enseignement);
            } else {
                Enseignement existing = enseignementRepository.findById(dto.getId()).orElse(null);
                if (existing != null && !existing.getNom().equals(dto.getNom())) {
                    existing.setNom(dto.getNom());
                    enseignementsToUpdate.add(existing);
                }
            }
        }

        if (!enseignementsToSave.isEmpty()) {
            enseignementRepository.saveAll(enseignementsToSave);
            logger.info("{} nouveaux enseignements ajoutés.", enseignementsToSave.size());
        }

        if (!enseignementsToUpdate.isEmpty()) {
            enseignementRepository.saveAll(enseignementsToUpdate);
            logger.info("{} enseignements mis à jour.", enseignementsToUpdate.size());
        }
    }
*/


    public  void save(Enseignement enseignement){
        enseignementRepository.save(enseignement);
    }


}
