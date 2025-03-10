//package sn.uasz.EmploisDuTempsBackend.Service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//import sn.uasz.EmploisDuTempsBackend.Authentification.model.Utilisateur;
//import sn.uasz.EmploisDuTempsBackend.Modele.ChoixLocal;
//import sn.uasz.EmploisDuTempsBackend.Modele.ECLocal;
//import sn.uasz.EmploisDuTempsBackend.Modele.EnseignementLocal;
//import sn.uasz.EmploisDuTempsBackend.Modele.UE;
//import sn.uasz.EmploisDuTempsBackend.Repository.ChoixLocalRepository;
//import sn.uasz.EmploisDuTempsBackend.Repository.ECRepository;
//import sn.uasz.EmploisDuTempsBackend.Repository.EnseignementLocalRepository;
//import sn.uasz.EmploisDuTempsBackend.Repository.UeRepository;
//import sn.uasz.EmploisDuTempsBackend.Utilisateur.UtilisateurRepository;
//import java.time.LocalDate;
//
////Nous allons écouter les événements du microservice Enseignement pour maintenir la copie locale.
//
//@Service
//public class EnseignementEventListener {
//    @Autowired
//    private EnseignementLocalRepository enseignementLocalRepository;
//    @Autowired
//    private ChoixLocalRepository choixLocalRepository;
//    @Autowired
//    private UtilisateurRepository utilisateurRepository;
//    @Autowired
//    private ECRepository ecRepository;
//    @Autowired
//    private UeRepository ueReposytorie;
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @KafkaListener(topics = "enseignement-events", groupId = "emploi-du-temps-group")
//    public void handleEnseignementEvent(String message) {
//        try {
//            JsonNode node = objectMapper.readTree(message);
//            Long enseignementId = node.get("id").asLong();
//            String action = node.get("action").asText();
//
//            if ("delete".equals(action)) {
//                enseignementLocalRepository.deleteById(enseignementId);
//            } else {
//                EnseignementLocal enseignement = enseignementLocalRepository.findById(enseignementId)
//                        .orElse(new EnseignementLocal());
//                enseignement.setValide(node.get("valide").asBoolean());
//                enseignementLocalRepository.save(enseignement);
//            }
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @KafkaListener(topics = "choix-events", groupId = "emploi-du-temps-group")
//    public void handleChoixEvent(String message) {
//        try {
//            JsonNode node = objectMapper.readTree(message);
//            Long choixId = node.get("id").asLong();
//            String action = node.get("action").asText();
//
//            if ("delete".equals(action)) {
//                choixLocalRepository.deleteById(choixId);
//            } else {
//                ChoixLocal choix = choixLocalRepository.findById(choixId).orElse(new ChoixLocal());
//                choix.setType(node.get("type").asText());
//                choix.setEnseignant(choix.getEnseignant());
//                choix.setEcId(node.get("ecId").asLong());
//                choix.setDate(LocalDate.parse(node.get("date").asText()));
//                choixLocalRepository.save(choix);
//            }
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @KafkaListener(topics = "utilisateur-events", groupId = "emploi-du-temps-group")
//    public void handleUtilisateurEvent(String message) {
//        try {
//            JsonNode node = objectMapper.readTree(message);
//            Long utilisateurId = node.get("id").asLong();
//            String action = node.get("action").asText();
//
//            if ("delete".equals(action)) {
//                utilisateurRepository.deleteById(utilisateurId);
//            } else {
//                Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId).orElse(new Utilisateur());
//                utilisateur.setUsername(node.get("username").asText());
//                utilisateur.setNom(node.get("nom").asText());
//                utilisateur.setPrenom(node.get("prenom").asText());
//                utilisateurRepository.save(utilisateur);
//            }
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @KafkaListener(topics = "ec-events", groupId = "emploi-du-temps-group")
//    public void handleEcEvent(String message) {
//        try {
//            JsonNode node = objectMapper.readTree(message);
//            Long ecId = node.get("id").asLong();
//            String action = node.get("action").asText();
//
//            if ("delete".equals(action)) {
//                ecRepository.deleteById(ecId);
//            } else {
//                ECLocal ecLocal = ecRepository.findById(ecId).orElse(new ECLocal());
//                ecLocal.setLibelle(node.get("libelle").asText());
//                ecLocal.setCode(node.get("code").asText());
//                ecRepository.save(ecLocal);
//            }
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @KafkaListener(topics = "ue-events", groupId = "emploi-du-temps-group")
//    public void handleUeEvent(String message) {
//        try {
//            JsonNode node = objectMapper.readTree(message);
//            Long ueId = node.get("id").asLong();
//            String action = node.get("action").asText();
//
//            if ("delete".equals(action)) {
//                ueReposytorie.deleteById(ueId);
//            } else {
//                UE ue = ueReposytorie.findById(ueId).orElse(new UE());
//                ue.setLibelle(node.get("libelle").asText());
//                ue.setCode(node.get("code").asText());
//                ueReposytorie.save(ue);
//            }
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }
//}
////Explication :
////
////Écoute les événements envoyés par le microservice Enseignement via Kafka.
////Désérialise le message en un objet EnseignementLocal et le stocke.