package uasz.sn.microservice_repartition.DTO;

public class EnseignementDTO {
    private Long id;
    private String formation;
    private int niveau;
    private int semestre;
    private String nom;

    public EnseignementDTO() {
    }

    public EnseignementDTO(Long id, String formation, int niveau, int semestre, String nom) {
        this.id = id;
        this.formation = formation;
        this.niveau = niveau;
        this.semestre = semestre;
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
