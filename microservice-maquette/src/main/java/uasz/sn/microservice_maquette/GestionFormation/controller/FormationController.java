package uasz.sn.microservice_maquette.GestionFormation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uasz.sn.microservice_maquette.GestionClasse.model.Classe;
import uasz.sn.microservice_maquette.GestionFormation.model.Formation;
import uasz.sn.microservice_maquette.GestionFormation.service.FormationService;

import java.util.List;

@RestController
@RequestMapping("/formations")
public class FormationController {
    @Autowired
    private FormationService formationService;

    @GetMapping("")
    public ResponseEntity<List<Formation>> voirFormations() {
        List<Formation> formations = formationService.findAll();
        return new ResponseEntity<>(formations, HttpStatus.OK);
    }

    @PostMapping("/ajouter")
    public ResponseEntity<Formation> ajouterFormation(@RequestBody Formation formation) {
        Formation nouvelleFormation = formationService.create(formation);
        return new ResponseEntity<>(nouvelleFormation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/modifier")
    public ResponseEntity<Formation> modifierFormation(@PathVariable Long id, @RequestBody Formation formation1) {
        Formation formation = formationService.findById(id);
        if (formation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (formation1.getIntitule() != null) {
            formation.setIntitule(formation1.getIntitule());
        }
        formation.setArchive(formation1.isArchive());
        Formation formationModifiee = formationService.update(formation);
        return new ResponseEntity<>(formationModifiee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/supprimer")
    public ResponseEntity<List<Formation>> supprimerFormation(@PathVariable Long id) {
        Formation formation = formationService.findById(id);
        if (formation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        formationService.delete(formation);
        List<Formation> formations = formationService.findAll();
        return new ResponseEntity<>(formations, HttpStatus.OK);
    }

    @PutMapping("/{id}/archiver")
    public ResponseEntity<Formation> archiver(@PathVariable Long id) {
        Formation formation = formationService.findById(id);
        if (formation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(formation.isArchive()){
            formation.setArchive(false);
        }else{
            formation.setArchive(true);
        }
        Formation formationArchivee = formationService.update(formation);
        return new ResponseEntity<>(formationArchivee, HttpStatus.OK);
    }

    @GetMapping("/{id}/classes")
    public ResponseEntity<List<Classe>> listeClasse(@PathVariable Long id) {
        Formation formation = formationService.findById(id);
        if (formation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Classe> classes = formation.getClasses();
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }
}