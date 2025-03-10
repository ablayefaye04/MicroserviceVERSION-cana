package sn.uasz.EmploisDuTempsBackend.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class EmploiDto {
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LigneEmploiDto> ligneEmploiDtos=new ArrayList<>();

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public List<LigneEmploiDto> getLigneEmploiDtos() {
        return ligneEmploiDtos;
    }
}
