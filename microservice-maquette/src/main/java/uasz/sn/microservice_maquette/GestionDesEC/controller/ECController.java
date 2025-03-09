package uasz.sn.microservice_maquette.GestionDesEC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uasz.sn.microservice_maquette.GestionDesEC.model.EC;
import uasz.sn.microservice_maquette.GestionDesEC.service.ECService;
import uasz.sn.microservice_maquette.GestionDesUE.model.UE;
import uasz.sn.microservice_maquette.GestionDesUE.service.UEService;
import uasz.sn.microservice_maquette.GestionEnseignement.model.Enseignement;
import uasz.sn.microservice_maquette.GestionEnseignement.service.EnseignementService;

import java.util.List;

@RestController
@RequestMapping("/ecs")
public class ECController {

    @Autowired
    private ECService ecService;
    @Autowired
    private UEService ueService;
    @Autowired
    private EnseignementService enseignementService;

    @GetMapping("")
    public ResponseEntity<?> voirListe() {
        List<EC> ecs = ecService.findAll();
        return ResponseEntity.ok(ecs);
    }

    @PostMapping("/{idUE}/ajouter")
    public ResponseEntity<?> ajouterEC(@PathVariable Long idUE, @RequestBody EC ec) {
        UE ue = ueService.findById(idUE);
        if (ue == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("L'ue n'existe pas");
        }
        ec.setUe(ue);
        EC nouvelleEC = ecService.create(ec);

        // Automatically assign EC to Enseignements for all Maquettes of the UE
        if (ue.getMaquettes() != null) {
            ue.getMaquettes().forEach(maquette -> {
                if(!enseignementService.Exist(nouvelleEC.getId(),maquette.getId())){
                    Enseignement enseignement = new Enseignement();
                    enseignement.setMaquette(maquette);
                    enseignement.setEc(nouvelleEC);
                    enseignementService.create(enseignement);
                }
                });
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(nouvelleEC);
    }

    @PutMapping("/{id}/modifier")
    public ResponseEntity<?> modifierEc(@PathVariable Long id, @RequestBody EC ec, @RequestParam(required = false) Long idUE) {
        EC ecUpdate = ecService.findById(id);
        if (ecUpdate == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Le ec n'existe pas ou est introuvable");
        }

        // Update fields conditionally
        if (idUE != null) {
            UE ue = ueService.findById(idUE);
            if (ue != null) {
                ecUpdate.setUe(ue);
            }
        }

        // Only update fields if the values have changed
        if (!ecUpdate.getCode().equals(ec.getCode())) {
            ecUpdate.setCode(ec.getCode());
        }
        if (!ecUpdate.getIntitule().equals(ec.getIntitule())) {
            ecUpdate.setIntitule(ec.getIntitule());
        }
        if (ecUpdate.getCM() != ec.getCM()) {
            ecUpdate.setCM(ec.getCM());
        }
        if (ecUpdate.getTP() != ec.getTP()) {
            ecUpdate.setTP(ec.getTP());
        }
        if (ecUpdate.getTD() != ec.getTD()) {
            ecUpdate.setTD(ec.getTD());
        }
        if (ecUpdate.getCoefficient() != ec.getCoefficient()) {
            ecUpdate.setCoefficient(ec.getCoefficient());
        }

        // Save the updated EC and assign it to Enseignements if necessary
        EC ec1 = ecService.update(ecUpdate);
        if (idUE != null) {
            UE ue = ueService.findById(idUE);
            if (ue.getMaquettes() != null) {
                ue.getMaquettes().forEach(maquette -> {
                    if(!enseignementService.Exist(ec1.getId(),maquette.getId())){
                        Enseignement enseignement = new Enseignement();
                        enseignement.setMaquette(maquette);
                        enseignement.setEc(ec1);
                        enseignementService.create(enseignement);
                    }
                });
            }
        }
        return ResponseEntity.ok(ec1);
    }

    @DeleteMapping("/{id}/supprimer")
    public ResponseEntity<?> supprimerEC(@PathVariable Long id) {
        EC ec = ecService.findById(id);
        if (ec == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ec n'existe pas ou introuuvable");
        }
        ecService.delete(ec);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/archiver")
    public ResponseEntity<Void> archiver(@PathVariable Long id) {
        EC ec = ecService.findById(id);
        if (ec == null) {
            return ResponseEntity.notFound().build();
        }
        ecService.archiver(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/activer")
    public ResponseEntity<Void> activer(@PathVariable Long id) {
        EC ec = ecService.findById(id);
        if (ec == null) {
            return ResponseEntity.notFound().build();
        }
        ecService.activer(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/details/{ueId}")
    public ResponseEntity<List<EC>> getECsByUE(@PathVariable Long ueId) {
        List<EC> ecs = ecService.findByUEId(ueId); // Récupère les ECs pour l'UE spécifiée
        return ResponseEntity.ok(ecs);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EC> getECDetails(@PathVariable Long id) {
        EC ec = ecService.findById(id); // Récupère l'EC par son ID
        if (ec == null) {
            return ResponseEntity.notFound().build(); // Retourne 404 si l'EC n'existe pas
        }
        return ResponseEntity.ok(ec);
    }
}
