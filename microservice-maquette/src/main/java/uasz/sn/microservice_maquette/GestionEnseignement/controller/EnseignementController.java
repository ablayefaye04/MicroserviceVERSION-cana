package uasz.sn.microservice_maquette.GestionEnseignement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uasz.sn.microservice_maquette.GestionEnseignement.DTO.EnseignementDTO;
import uasz.sn.microservice_maquette.GestionEnseignement.service.EnseignementService;

import java.util.List;

@RestController
@RequestMapping("/enseignements")
public class EnseignementController {
    @Autowired
    private EnseignementService enseignementService;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(enseignementService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        EnseignementDTO enseignement = enseignementService.getEnseignementDTO(id);
        if(enseignement == null){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("L'enseignement n'existe pas");
        }
        return ResponseEntity.ok(enseignement);
    }


    /*@GetMapping("/{id}/info")
    public ResponseEntity<?> getNom(@PathVariable Long id){
        Enseignement enseignement = enseignementService.findById(id);
        List<String> info = new ArrayList<>();
        if(enseignement != null){
            String nom = enseignement.getEc().getIntitule();
            String semestre =""+enseignement.getMaquette().getSemestre();
            String niveau = ""+enseignement.getMaquette().getClasse().getNiveau();
            String formation = enseignement.getMaquette().getClasse().getFormation().getIntitule();
            info.add(nom);info.add(semestre);info.add(niveau);info.add(formation);
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("L'enseignement n'existe pas");
        }
        return ResponseEntity.ok(info);
    }*/

    @GetMapping("/esclaves")
    public ResponseEntity<?> getEsclave(){
        List<EnseignementDTO> enseignementDTOS = enseignementService.getEnseignementsDTO();
        if(enseignementDTOS == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("mal recuperations de enseignements");
        }
        System.out.println("enseignement trouvé");
        return ResponseEntity.ok(enseignementDTOS);
    }
}
