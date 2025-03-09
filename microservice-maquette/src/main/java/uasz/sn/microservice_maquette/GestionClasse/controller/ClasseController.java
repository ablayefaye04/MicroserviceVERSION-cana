package uasz.sn.microservice_maquette.GestionClasse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uasz.sn.microservice_maquette.GestionClasse.model.Classe;
import uasz.sn.microservice_maquette.GestionClasse.service.ClasseService;
import uasz.sn.microservice_maquette.GestionFormation.model.Formation;
import uasz.sn.microservice_maquette.GestionFormation.service.FormationService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Autoriser les requêtes depuis React

@RequestMapping("/classes")
public class ClasseController {
    private final ClasseService classeService;
    private final FormationService formationService;

    public ClasseController(ClasseService classeService, FormationService formationService) {
        this.classeService = classeService;
        this.formationService = formationService;
    }

    @GetMapping("")
    public ResponseEntity<List<Classe>> liste() {
        return ResponseEntity.ok(classeService.findAll());
    }

    @PostMapping("/{idFormation}/ajouter")
    public ResponseEntity<?> ajouter(@PathVariable Long idFormation, @RequestBody Classe classe) {
        Formation formation = formationService.findById(idFormation);
        if (formation != null) {
            classe.setFormation(formation);
            Classe nouvelleClasse = classeService.create(classe);
            return ResponseEntity.status(201).body(nouvelleClasse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("La Formation n'existe pas");
    }

    @PutMapping("/{id}/modifier")
    public ResponseEntity<?> modifier(@PathVariable Long id, @RequestBody Classe classeDetails) {
        Classe classe = classeService.findById(id);
        if (classe != null) {
            if (classeDetails.getFormation() != null) {
                classe.setFormation(classeDetails.getFormation());
            }
            classe.setNiveau(classeDetails.getNiveau());
            if(classe.isArchive() != classeDetails.isArchive()){
                classe.setArchive(classeDetails.isArchive());
            }
            Classe updatedClasse = classeService.update(classe);
            return ResponseEntity.ok(updatedClasse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("La classe n'existe pas");
    }

    @DeleteMapping("/{id}/supprimer")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Classe classe = classeService.findById(id);
        if (classe != null) {
            classeService.delete(classe);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("La classe n'existe pas");
    }
    @GetMapping("/byFormation/{idFormation}")
    public ResponseEntity<List<Classe>> getClassesByFormation(@PathVariable Long idFormation) {
        List<Classe> classes = classeService.findByFormationId(idFormation);
        return ResponseEntity.ok(classes);
    }
}
