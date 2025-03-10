package sn.uasz.EmploisDuTempsBackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.uasz.EmploisDuTempsBackend.Authentification.model.Utilisateur;
import sn.uasz.EmploisDuTempsBackend.Utilisateur.UtilisateurRepository;

@Service
@Transactional
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur findUtilisateur(String username) {
        return utilisateurRepository.findByUsername(username);
    }

}