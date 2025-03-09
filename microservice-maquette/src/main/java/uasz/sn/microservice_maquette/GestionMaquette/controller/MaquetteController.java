package uasz.sn.microservice_maquette.GestionMaquette.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uasz.sn.microservice_maquette.GestionClasse.model.Classe;
import uasz.sn.microservice_maquette.GestionClasse.service.ClasseService;
import uasz.sn.microservice_maquette.GestionDesEC.model.EC;
import uasz.sn.microservice_maquette.GestionDesEC.service.ECService;
import uasz.sn.microservice_maquette.GestionDesUE.model.UE;
import uasz.sn.microservice_maquette.GestionDesUE.service.UEService;
import uasz.sn.microservice_maquette.GestionEnseignement.model.Enseignement;
import uasz.sn.microservice_maquette.GestionEnseignement.service.EnseignementService;
import uasz.sn.microservice_maquette.GestionMaquette.model.Maquette;
import uasz.sn.microservice_maquette.GestionMaquette.service.MaquetteService;

import java.util.List;

@RestController
@RequestMapping("/maquettes")
public class MaquetteController {

    @Autowired
    private MaquetteService maquetteService;
    @Autowired
    private UEService ueService;
    @Autowired
    private ClasseService classeService;
    @Autowired
    private ECService ecService;
    @Autowired
    private EnseignementService enseignementService;

    @GetMapping("")
    public ResponseEntity<List<Maquette>> afficherMaquette() {
        List<Maquette> maquettes = maquetteService.findAll();
        return new ResponseEntity<>(maquettes, HttpStatus.OK);
    }

    @PostMapping("/ajouter")
    public ResponseEntity<Maquette> ajouterMaquette(@RequestParam Long classe, @RequestParam Long[] idUEs, @RequestParam int semestre) {
        Maquette maquette = new Maquette();
        Classe classe1 = classeService.findById(classe);
        if (classe1 == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // Handle error if class is not found
        }
        maquette.setNom(classe1.getFormation().getIntitule() + '-' + classe1.getNiveau());
        maquette.setActive(true);
        maquette.setArchive(false);
        maquette.setSemestre(semestre);
        maquette.setClasse(classe1);
        if (idUEs != null) {
            maquette.setUeList(ueService.findByIds(idUEs));
        }
        Maquette maquette1 = maquetteService.create(maquette);
        if(idUEs != null){
            List<UE> ues = ueService.findByIds(idUEs);
            ues.forEach(ue->{
                for(EC ec: ue.getEcList()){
                    if(!enseignementService.Exist(ec.getId(),maquette1.getId())){
                        Enseignement enseignement = new Enseignement();
                        enseignement.setEc(ec);
                        enseignement.setMaquette(maquette);
                        enseignementService.create(enseignement);
                    }
                }
            });
         }
        return ResponseEntity.status(HttpStatus.CREATED).body(maquette1);
    }

    @PostMapping("/Maquette/modifier")
    public ResponseEntity<Maquette> modifierMaquette(@RequestParam Long id, @RequestParam String nom, @RequestParam int semestre, @RequestParam Long[] idUEs) {
        Maquette maquette = maquetteService.findById(id);
        if (maquette == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Return 404 if maquette is not found
        }
        if (!maquette.getNom().equals(nom)) {
            maquette.setNom(nom);
        }
        if (maquette.getSemestre() != semestre) {
            maquette.setSemestre(semestre);
        }
        if (idUEs != null) {
            maquette.setUeList(ueService.findByIds(idUEs));
        }
        Maquette maquette1 = maquetteService.update(maquette);
        if(idUEs != null){
            List<UE> ues = ueService.findByIds(idUEs);
            ues.forEach(ue->{
                for(EC ec: ue.getEcList()){
                    if(!enseignementService.Exist(ec.getId(),maquette1.getId())){
                        Enseignement enseignement = new Enseignement();
                        enseignement.setEc(ec);
                        enseignement.setMaquette(maquette);
                        enseignementService.create(enseignement);
                    }
                }
            });
        }
        return new ResponseEntity<>(maquette1, HttpStatus.OK);
    }

    @PostMapping("/{id}/supprimer")
    public ResponseEntity<String> supprimerMaquette(@PathVariable Long id) {
        Maquette maquette = maquetteService.findById(id);
        if (maquette == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Maquette not found");
        }
        maquetteService.delete(maquette);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Maquette deleted");
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<Maquette> detailMaquette(@PathVariable Long id) {
        Maquette maquette = maquetteService.findById(id);
        if (maquette == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(maquette, HttpStatus.OK);
    }

    @PostMapping("/{id}/archiver")
    public ResponseEntity<String> archiver(@PathVariable Long id) {
        Maquette maquette = maquetteService.findById(id);
        if (maquette == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Maquette not found");
        }
        maquetteService.archiver(id);
        return ResponseEntity.status(HttpStatus.OK).body("Maquette archived");
    }

    @PostMapping("/{id}/activer")
    public ResponseEntity<String> activer(@PathVariable Long id) {
        Maquette maquette = maquetteService.findById(id);
        if (maquette == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Maquette not found");
        }
        maquetteService.activer(id);
        return ResponseEntity.status(HttpStatus.OK).body("Maquette activated");
    }
}
