package dev.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Collegue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

    private String email;

    private String motDePasse;

    @OneToMany(mappedBy = "collegue", cascade = CascadeType.PERSIST)
    private List<RoleCollegue> roles;
    
    @OneToMany(mappedBy = "collegue_abs", cascade = CascadeType.PERSIST)
    private List<Absence> absences;
    
    private int nbRtt;
   
    private int nbCongesPayes;
    
    public Collegue() {
		super();
	}

	public Collegue(Long id, String nom, String prenom, String email, String motDePasse, List<RoleCollegue> roles,
			List<Absence> absences, int nbRtt, int nbCongesPayes) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.roles = roles;
		this.absences = absences;
		this.nbRtt = nbRtt;
		this.nbCongesPayes = nbCongesPayes;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public List<RoleCollegue> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleCollegue> roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

	/**
	 * Getter
	 * @return the absences
	 */
	public List<Absence> getAbsences() {
		return absences;
	}

	/**
	 * Setter
	 * @param absences the absences to set
	 */
	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}

	/**
	 * Getter
	 * @return the nbRtt
	 */
	public int getNbRtt() {
		return nbRtt;
	}

	/**
	 * Setter
	 * @param nbRtt the nbRtt to set
	 */
	public void setNbRtt(int nbRtt) {
		this.nbRtt = nbRtt;
	}

	/**
	 * Getter
	 * @return the nbCongesPayes
	 */
	public int getNbCongesPayes() {
		return nbCongesPayes;
	}

	/**
	 * Setter
	 * @param nbCongesPayes the nbCongesPayes to set
	 */
	public void setNbCongesPayes(int nbCongesPayes) {
		this.nbCongesPayes = nbCongesPayes;
	}
    
    
}
