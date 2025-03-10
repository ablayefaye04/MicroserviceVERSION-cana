package sn.uasz.EmploisDuTempsBackend.Controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sn.uasz.EmploisDuTempsBackend.Authentification.model.Utilisateur;
import sn.uasz.EmploisDuTempsBackend.Modele.*;
import sn.uasz.EmploisDuTempsBackend.Repository.*;
import sn.uasz.EmploisDuTempsBackend.Service.EmploisDuTempsService;
import sn.uasz.EmploisDuTempsBackend.Service.UtilisateurService;
import sn.uasz.EmploisDuTempsBackend.Utilisateur.UtilisateurRepository;
import sn.uasz.EmploisDuTempsBackend.Utilisateur.repository.EmploiDuTempsRepository;
import sn.uasz.EmploisDuTempsBackend.dto.*;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class EmploiDuTempsController {
    private final EmploisDuTempsService emploiDuTempsService;
    private final UtilisateurService utilisateurService;
    private final SalleRepository salleRepository;
    private final EnseignementLocalRepository enseignementRepository;
    private final EmploiDuTempsRepository emploiDuTempsRepository;
    private final LigneEmploiDuTempsRepository ligneEmploiDuTempsRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ECRepository ecRepository;
    private final ClasseRepository classeRepository;

    public EmploiDuTempsController(EmploisDuTempsService emploiDuTempsService, UtilisateurService utilisateurService, SalleRepository salleRepository, EnseignementLocalRepository enseignementRepository, EmploiDuTempsRepository emploiDuTempsRepository, LigneEmploiDuTempsRepository ligneEmploiDuTempsRepository, UtilisateurRepository utilisateurRepository, ECRepository ecRepository, ClasseRepository classeRepository) {
        this.emploiDuTempsService = emploiDuTempsService;
        this.utilisateurService = utilisateurService;
        this.salleRepository = salleRepository;
        this.enseignementRepository = enseignementRepository;
        this.emploiDuTempsRepository = emploiDuTempsRepository;
        this.ligneEmploiDuTempsRepository = ligneEmploiDuTempsRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.ecRepository = ecRepository;
        this.classeRepository = classeRepository;
    }

    @GetMapping("/ChefDepartement/EmploiDuTemps")
    public ResponseEntity<List<EmploiPersoDto>> affiche(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        System.out.println("Prof vacataire : " + email);

        Utilisateur utilisateur = utilisateurRepository.findByUsername(email);
        if (utilisateur == null) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        List<EmploiPersoDto> emploiPersoDtos = new ArrayList<>();
        System.out.println(utilisateur.getClass());
        List<EmploiDuTemps> emploiDuTemps = emploiDuTempsRepository.findByEnseignantId(utilisateur.getId());

        for (EmploiDuTemps emploi : emploiDuTemps) {
            for (LigneEmploiDuTemps ligneEmploiDuTemps : emploi.getLigneEmploiDuTemps()) {
                if (ligneEmploiDuTemps.getEnseignementLocal().getChoixLocal().getEnseignant().getId()
                        .equals(utilisateur.getId()))
                {
                    EmploiPersoDto emploiPersoDto = new EmploiPersoDto();
                    System.out.println("Emploi : " + ligneEmploiDuTemps.getHeureDebut());

                    emploiPersoDto.setEc(ecRepository.findById(ligneEmploiDuTemps.getEnseignementLocal().getChoixLocal().getEcId())
                            .map(ECLocal::getLibelle)
                            .orElse("EC inconnu"));

                    emploiPersoDto.setSalle(ligneEmploiDuTemps.getSalle().getLibelle());
                    emploiPersoDto.setType(ligneEmploiDuTemps.getEnseignementLocal().getChoixLocal().getType());
                    emploiPersoDto.setDebut(ligneEmploiDuTemps.getHeureDebut());
                    emploiPersoDto.setJour(ligneEmploiDuTemps.getJour());
                    emploiPersoDto.setFin(ligneEmploiDuTemps.getHeureFin());
                    emploiPersoDto.setNom(utilisateur.getNom());
                    emploiPersoDto.setPrenom(utilisateur.getPrenom());

                    emploiPersoDtos.add(emploiPersoDto);
                }
            }
        }
        return ResponseEntity.ok(emploiPersoDtos);
    }

    @GetMapping("/Vacataire/EmploiDuTemps/{email}")
    public ResponseEntity<List<EmploiPersoDto>> afficheVac(@PathVariable String email) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//        System.out.println("Prof vacataire : " + email);

        Utilisateur utilisateur = utilisateurRepository.findByUsername(email);
        if (utilisateur == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.emptyList());
        }


        List<EmploiPersoDto> emploiPersoDtos = new ArrayList<>();
        List<EmploiDuTemps> emploiDuTemps = emploiDuTempsRepository.findByEnseignantId(utilisateur.getId());

        for (EmploiDuTemps emploi : emploiDuTemps) {
            for (LigneEmploiDuTemps ligneEmploiDuTemps : emploi.getLigneEmploiDuTemps()) {
                if (ligneEmploiDuTemps.getEnseignementLocal().getChoixLocal().getEnseignant().getId()==utilisateur.getId())
                {
                    EmploiPersoDto emploiPersoDto = new EmploiPersoDto();
                    System.out.println("Emploi : " + ligneEmploiDuTemps.getHeureDebut());

                    emploiPersoDto.setEc(ecRepository.findById(ligneEmploiDuTemps.getEnseignementLocal().getChoixLocal().getEcId())
                            .map(ECLocal::getLibelle)
                            .orElse("EC inconnu"));

                    emploiPersoDto.setSalle(ligneEmploiDuTemps.getSalle().getLibelle());
                    emploiPersoDto.setType(ligneEmploiDuTemps.getEnseignementLocal().getChoixLocal().getType());
                    emploiPersoDto.setDebut(ligneEmploiDuTemps.getHeureDebut());
                    emploiPersoDto.setJour(ligneEmploiDuTemps.getJour());
                    emploiPersoDto.setFin(ligneEmploiDuTemps.getHeureFin());
                    emploiPersoDto.setNom(utilisateur.getNom());
                    emploiPersoDto.setPrenom(utilisateur.getPrenom());

                    emploiPersoDtos.add(emploiPersoDto);
                }
            }
        }
        return ResponseEntity.ok(emploiPersoDtos);
    }

    @GetMapping("/Permanent/EmploiDuTemps")
    public ResponseEntity<List<EmploiPersoDto>> affichePer(Principal principal) {
        String email = principal.getName();
        Utilisateur utilisateur = utilisateurRepository.findByUsername(email);

        if (utilisateur == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<EmploiPersoDto> emploiPersoDtos = new ArrayList<>();

        // Charger uniquement les emplois du temps pertinents
        List<LigneEmploiDuTemps> lignes = ligneEmploiDuTempsRepository.findByEnseignementLocal_ChoixLocal_EnseignantId(utilisateur.getId());

        for (LigneEmploiDuTemps ligne : lignes) {
            EmploiPersoDto emploiPersoDto = new EmploiPersoDto();

            emploiPersoDto.setEc(ecRepository.findById(ligne.getEnseignementLocal().getChoixLocal().getEcId())
                    .map(ECLocal::getLibelle).orElse("EC inconnu"));
            emploiPersoDto.setSalle(ligne.getSalle().getLibelle());
            emploiPersoDto.setType(ligne.getEnseignementLocal().getChoixLocal().getType());
            emploiPersoDto.setDebut(ligne.getHeureDebut());
            emploiPersoDto.setJour(ligne.getJour());
            emploiPersoDto.setFin(ligne.getHeureFin());
            emploiPersoDto.setNom(utilisateur.getNom());
            emploiPersoDto.setPrenom(utilisateur.getPrenom());

            emploiPersoDtos.add(emploiPersoDto);
        }

        return ResponseEntity.ok(emploiPersoDtos);
    }

    @PostMapping("/ChefDepartement/GestionEmploiDuTemps")
    public ResponseEntity<EmploiResponseDto> addEmploi(@Valid @RequestBody EmploiDto emploiDto,
                                                       BindingResult result,
                                                       Principal principal) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        System.out.println("Emploi du temps reçu: " + emploiDto);

        EmploiDuTemps emploiDuTemps = new EmploiDuTemps();

        List<LigneEmploiDuTemps> lignes = new ArrayList<>();


        for (LigneEmploiDto ligneDto : emploiDto.getLigneEmploiDtos()) {
            // Vérification de la salle
            Salle salle = salleRepository.findById(ligneDto.getSalleId())
                    .orElseThrow(() -> new EntityNotFoundException("Salle non trouvée avec id: " + ligneDto.getSalleId()));

            // Vérification de l'enseignement local
            EnseignementLocal enseignement = enseignementRepository.findById(ligneDto.getEnseignementId())
                    .orElseThrow(() -> new EntityNotFoundException("Enseignement non trouvé avec id: " + ligneDto.getEnseignementId()));

            // Vérification des conflits d’horaires
            boolean conflit = ligneEmploiDuTempsRepository.existsBySalleAndJourAndHeureDebutBetween(
                    salle, ligneDto.getJour(), ligneDto.getHeureDebut(), ligneDto.getHeureFin()
            );

            if (conflit) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }

            // Création de la ligne d'emploi du temps
            LigneEmploiDuTemps ligne = new LigneEmploiDuTemps();
            ligne.setEnseignementLocal(enseignement);
            ligne.setSalle(salle);
            ligne.setHeureDebut(ligneDto.getHeureDebut());
            ligne.setHeureFin(ligneDto.getHeureFin());
            ligne.setJour(ligneDto.getJour());
            ligne.setEmploiDuTemps(emploiDuTemps);

            lignes.add(ligne);
        }

        // Création de l'emploi du temps
        emploiDuTemps.setDateDebut(LocalDate.from(emploiDto.getDateDebut()));
        emploiDuTemps.setDateFin(LocalDate.from(emploiDto.getDateFin()));
        emploiDuTemps.setLigneEmploiDuTemps(lignes);

        // Sauvegarde en cascade
        emploiDuTempsRepository.save(emploiDuTemps);

        // Récupération des données à retourner
        Utilisateur user = utilisateurService.findUtilisateur(principal.getName());
        List<EnseignementLocal> enseignementsDisponibles = enseignementRepository.findEnseignementsNonUtilises();
        List<EmploiDuTemps> emploiDuTempsList = emploiDuTempsService.findAllEmploiDuTemps();

        EmploiResponseDto emploiResponseDto = new EmploiResponseDto(
                enseignementsDisponibles, emploiDuTempsList, salleRepository.findAll(),
                user.getNom(), user.getPrenom()
        );

        return ResponseEntity.ok(emploiResponseDto);
    }

    @GetMapping("/ChefDepartement/GestionEmploiDuTemps/supprimer/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<EmploiDuTemps> emploiDuTempsOpt = emploiDuTempsRepository.findById(id);

        if (emploiDuTempsOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erreur : Emploi du temps avec l'ID " + id + " non trouvé.");
        }

        EmploiDuTemps emploiDuTemps = emploiDuTempsOpt.get();

        // Supprime d'abord les lignes d'emploi du temps associées
        ligneEmploiDuTempsRepository.deleteAll(emploiDuTemps.getLigneEmploiDuTemps());

        // Ensuite, supprime l'emploi du temps lui-même
        emploiDuTempsRepository.delete(emploiDuTemps);

        return ResponseEntity.ok("Emploi du temps supprimé avec succès.");
    }

    @GetMapping("/afficherEmploiParId/{id}")
    public ResponseEntity<List<LigneEmploiDuTemps>> afficherAllEmploi(@PathVariable Long id) {
        Optional<EmploiDuTemps> emploiDuTempsOpt = emploiDuTempsRepository.findById(id);
        if (emploiDuTempsOpt.isPresent()) {
            EmploiDuTemps emploiDuTemps = emploiDuTempsOpt.get();
            System.out.println("Emploi du temps trouvé: " + emploiDuTemps);
            return ResponseEntity.ok(emploiDuTemps.getLigneEmploiDuTemps());
        } else {
            System.out.println("Emploi du temps non trouvé pour l'ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    @GetMapping("/afficherEmploiParClasse/{libelle}")
    public ResponseEntity<List<LigneEmploiDuTempsDto>> afficherEmploiParClasse(@PathVariable String libelle) {
        // Récupérer l'ID de la classe à partir du libellé
        Classe classe = classeRepository.findByLibelle(libelle);

        if (classe == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }

        // Récupérer les lignes d'emploi du temps pour cette classe
        List<LigneEmploiDuTemps> lignesEmploi = ligneEmploiDuTempsRepository.findByEnseignementLocal_ChoixLocal_ClasseId(classe.getId());

        if (lignesEmploi.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }

        // Conversion des résultats en DTO
        List<LigneEmploiDuTempsDto> lignesEmploiDtos = new ArrayList<>();
        for (LigneEmploiDuTemps ligne : lignesEmploi) {
            LigneEmploiDuTempsDto dto = new LigneEmploiDuTempsDto();
            dto.setId(ligne.getId());
            dto.setHeureDebut(ligne.getHeureDebut());
            dto.setHeureFin(ligne.getHeureFin());
            dto.setJour(ligne.getJour());

            // Vérification du mapping de la salle
            if (ligne.getSalle() != null) {
                dto.setSalle(ligne.getSalle().getLibelle());
                System.out.println("Salle: " + ligne.getSalle().getLibelle());  // Log pour vérifier
            } else {
                dto.setSalle("Salle non trouvée");
            }

            // Vérification du mapping de l'enseignement local
            if (ligne.getEnseignementLocal() != null && ligne.getEnseignementLocal().getChoixLocal() != null) {
                Long ecId = ligne.getEnseignementLocal().getChoixLocal().getEcId();
                ECLocal ecLocal = ecRepository.findById(ecId).orElse(null);
                if (ecLocal != null) {
                    dto.setEnseignementLocal(ecLocal.getLibelle());
                    System.out.println("EnseignementLocal: " + ecLocal.getLibelle());  // Log pour vérifier
                } else {
                    dto.setEnseignementLocal("Enseignement non trouvé");
                }
            } else {
                dto.setEnseignementLocal("Enseignement local non trouvé");
            }

            lignesEmploiDtos.add(dto);
        }

        return ResponseEntity.ok(lignesEmploiDtos);
    }



}