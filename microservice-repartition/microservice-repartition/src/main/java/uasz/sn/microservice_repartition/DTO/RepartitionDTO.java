package uasz.sn.microservice_repartition.DTO;

public class RepartitionDTO {
    private String type;
    private Long enseignantId;
    private Long enseignementId;
    private boolean valide;

    // Getters et setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getEnseignantId() {
        return enseignantId;
    }

    public void setEnseignantId(Long enseignantId) {
        this.enseignantId = enseignantId;
    }

    public Long getEnseignementId() {
        return enseignementId;
    }

    public void setEnseignementId(Long enseignementId) {
        this.enseignementId = enseignementId;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }
}