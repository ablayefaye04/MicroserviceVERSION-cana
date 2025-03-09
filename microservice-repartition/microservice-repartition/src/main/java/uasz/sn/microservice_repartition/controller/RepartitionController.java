package uasz.sn.microservice_repartition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uasz.sn.microservice_repartition.DTO.RepartitionDTO;
import uasz.sn.microservice_repartition.model.Enseignant;
import uasz.sn.microservice_repartition.model.Enseignement;
import uasz.sn.microservice_repartition.model.Repartition;
import uasz.sn.microservice_repartition.service.EnseignantService;
import uasz.sn.microservice_repartition.service.EnseignementService;
import uasz.sn.microservice_repartition.service.RepartitionService;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/choix")
public class RepartitionController {
    @Autowired
    private RepartitionService repartitionService;
    @Autowired
    private EnseignementService enseignementService;

    @Autowired
    private EnseignantService enseignantService;
    @GetMapping("")
    public ResponseEntity<List<Repartition>> findAll() {
        return ResponseEntity.ok(repartitionService.findAll());
    }

    @GetMapping("/enseignants")
    public ResponseEntity<?> getEnseignants(){
        return ResponseEntity.ok(enseignantService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Repartition repartition = repartitionService.findById(id);
        if (repartition == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Le choix avec cet id("+id+") n'existe pas");
        } else {
            return ResponseEntity.ok(repartition);
        }
    }

   /* @GetMapping("{id}/enseignement")
    public ResponseEntity<?> getEnseignement(@PathVariable Long id){
        Repartition repartition = repartitionService.findById(id);
        if( repartition == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("repartition n'existe pas");
        }
        return  ResponseEntity.ok(repartition.getEnseignement());
    }*/
    @PutMapping("/{id}/valider")
    public ResponseEntity<?> valider(@PathVariable Long id) {
        Repartition repartition = repartitionService.valider(id);
        if (repartition == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("vous ne pouvez pas valider le choix car il est null");
        }
        return ResponseEntity.ok(repartition);
    }

    @PutMapping("/{id}/modifier")
    public ResponseEntity<?> modifierChoix(
            @PathVariable Long id,
            @RequestBody Repartition repartitionUpdate
    ) {
        Repartition repartitionExisting = repartitionService.findByEnseignantAndEnseignementAndType(
                repartitionUpdate.getEnseignant(),
                repartitionUpdate.getEnseignement(),
                repartitionUpdate.getType()
        );

        // Si la repartition existe déjà, renvoyer un message avec un code HTTP 409 (Conflict)
        if(repartitionExisting != null){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("L'enseignant a déjà fait le choix");  // Message d'erreur
        }

        Repartition repartition = repartitionService.findById(id);
        if (repartition != null) {
            if (repartition.getType() != null && !repartition.getType().equals(repartitionUpdate.getType())) {
                repartition.setType(repartitionUpdate.getType());
            }
            repartition.setDateChoix(LocalDate.now());
            if ((repartition.getEnseignant() != repartitionUpdate.getEnseignant()) && (repartitionUpdate.getEnseignant() != null)) {
                repartition.setEnseignant(repartitionUpdate.getEnseignant());
            }
            if ((repartition.getEnseignement() != repartitionUpdate.getEnseignement()) && (repartitionUpdate.getEnseignement() != null)) {
                repartition.setEnseignement(repartitionUpdate.getEnseignement());
            }
            repartition.setValide(repartitionUpdate.isValide());
            Repartition repartition1 = repartitionService.update(repartition);
            return ResponseEntity.ok(repartition1);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/supprimer")
    public ResponseEntity<?> supprimer(@PathVariable Long id){
        Repartition repartition = repartitionService.findById(id);
        if(repartition != null){
            repartitionService.delete(repartition);
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Le choix a été supprimé");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouter(@RequestBody RepartitionDTO repartitionDTO) {
        // Récupérer les objets complets à partir des IDs
        Enseignant enseignant = enseignantService.findById(repartitionDTO.getEnseignantId());
        Enseignement enseignement = enseignementService.findById(repartitionDTO.getEnseignementId());

        // Vérifier si une répartition avec les mêmes enseignant, enseignement et type existe déjà
        Repartition repartitionExisting = repartitionService.findByEnseignantAndEnseignementAndType(
                enseignant,
                enseignement,
                repartitionDTO.getType()
        );

        // Si la répartition existe déjà, renvoyer un message avec un code HTTP 409 (Conflict)
        if (repartitionExisting != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("L'enseignant a déjà fait le choix");
        }

        // Créer une nouvelle répartition
        Repartition repartition = new Repartition();
        repartition.setType(repartitionDTO.getType());
        repartition.setEnseignant(enseignant);
        repartition.setEnseignement(enseignement);
        repartition.setValide(repartitionDTO.isValide());
        repartition.setDateChoix(LocalDate.now());

        // Enregistrer la répartition
        Repartition repartition1 = repartitionService.create(repartition);
        return ResponseEntity.ok(repartition1);
    }
    /*@GetMapping("/{id}/infos")
    public ResponseEntity<?> getInfo(@PathVariable Long id){
        Repartition repartition = repartitionService.findById(id);
        if(repartition == null){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("repartition null");
        }
        String enseignement = repartition.getEnseignement().getNom() +'('+repartition.getEnseignement().getNiveau()+") semestre(" + repartition.getEnseignement().getSemestre()+')';
        String enseignant = repartition.getEnseignant().getPrenom() +' '+repartition.getEnseignant().getNom();
        String type = repartition.getType();
        List<String> infos = new ArrayList<>();
        infos.add(enseignement);infos.add(enseignant);infos.add(type);
        return ResponseEntity.ok(infos);
    }*/

    @GetMapping("/enseignements")
    public ResponseEntity<?> getEnseignements(){
        return ResponseEntity.ok(enseignementService.findAll());
    }

}
