package dev.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import dev.domain.enumerations.Departement;

/**
 * Classe entite qui represente un collegue.
 * @author eltahhansana
 *
 */
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
    
    @Enumerated(EnumType.STRING)
    private Departement departement;
    
    public Collegue() {
		super();
	}

	/** Constructeur
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param motDePasse
	 * @param roles
	 * @param absences
	 * @param nbRtt
	 * @param nbCongesPayes
	 */
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

	
	/** Getter
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/** Setter
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/** Getter
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/** Setter
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/** Getter
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/** Setter
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/** Getter
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/** Setter
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/** Getter
	 * @return the motDePasse
	 */
	public String getMotDePasse() {
		return motDePasse;
	}

	/** Setter
	 * @param motDePasse the motDePasse to set
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	/** Getter
	 * @return the roles
	 */
	public List<RoleCollegue> getRoles() {
		return roles;
	}

	/** Setter
	 * @param roles the roles to set
	 */
	public void setRoles(List<RoleCollegue> roles) {
		this.roles = roles;
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

	/**
	 * @return the departement
	 */
	public Departement getDepartement() {
		return departement;
	}

	/**
	 * @param departement the departement to set
	 */
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
    
    
}
