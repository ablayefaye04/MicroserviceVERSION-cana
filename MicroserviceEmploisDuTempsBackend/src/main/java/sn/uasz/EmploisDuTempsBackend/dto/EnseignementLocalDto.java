package sn.uasz.EmploisDuTempsBackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnseignementLocalDto {
    private Long ecId;
    private LocalDate date;
    private Long classeId;
    private EnseignantDto enseignant;

    public void setEcId(Long ecId) {
        this.ecId = ecId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setClasseId(Long classeId) {
        this.classeId = classeId;
    }

    public void setEnseignant(EnseignantDto enseignant) {
        this.enseignant = enseignant;
    }
}

