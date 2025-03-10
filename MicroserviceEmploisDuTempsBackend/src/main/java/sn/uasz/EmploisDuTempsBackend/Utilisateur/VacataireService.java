package sn.uasz.EmploisDuTempsBackend.Utilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.uasz.EmploisDuTempsBackend.Authentification.model.Utilisateur;

@Service
public class VacataireService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    public Utilisateur findUtilisateur(String username) {
        return utilisateurRepository.findByUsername(username);
    }
}