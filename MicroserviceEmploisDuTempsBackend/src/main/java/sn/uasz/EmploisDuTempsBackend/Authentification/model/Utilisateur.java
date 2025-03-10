package sn.uasz.EmploisDuTempsBackend.Authentification.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String username;
    @NotNull
    private String password;
    private String nom;
    private String prenom;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    private boolean active;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public boolean isActive() {
        return active;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}