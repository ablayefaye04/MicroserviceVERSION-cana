package sn.uasz.EmploisDuTempsBackend.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.uasz.EmploisDuTempsBackend.Modele.EmploiDuTemps;
import sn.uasz.EmploisDuTempsBackend.Utilisateur.repository.EmploiDuTempsRepository;

import java.util.List;

@Service
@Transactional
public class EmploisDuTempsService {@Autowired
private EmploiDuTempsRepository emploiDuTempsRepository;

    public void addEmploiDuTemps(EmploiDuTemps EmploiDuTemps) {
        emploiDuTempsRepository.save(EmploiDuTemps);
    }

    public List<EmploiDuTemps> findAllEmploiDuTemps() {
        return emploiDuTempsRepository.findAll();
    }

    public EmploiDuTemps getEmploiDuTemps(Long id) {
        return emploiDuTempsRepository.findById(id).get();
    }

    public void updateEmploiDuTemps(EmploiDuTemps ue) {
        emploiDuTempsRepository.save(ue);
    }

    public void deleteEmploiDuTemps(Long id) {
        emploiDuTempsRepository.deleteById(id);
    }


    public EmploiDuTemps findById(Long id) {
        return emploiDuTempsRepository.findById(id).get();}
}
