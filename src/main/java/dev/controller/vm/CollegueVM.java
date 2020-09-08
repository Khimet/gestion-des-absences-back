package dev.controller.vm;

import dev.domain.Collegue;
import dev.domain.enumerations.Departement;
import dev.domain.enumerations.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Structure modèlisant un collègue servant à communiquer avec l'extérieur (WEB
 * API).
 */
public class CollegueVM {

	private long id;
	private String email;
	private String nom;
	private String prenom;
	private List<Role> roles = new ArrayList<>();
	private int nbRtt;
	private int nbCongesPayes;
	private Departement departement;

	public CollegueVM(Collegue col) {
		this.id = col.getId();
		this.email = col.getEmail();
		this.nom = col.getNom();
		this.prenom = col.getPrenom();
		this.roles = col.getRoles().stream().map(roleCollegue -> roleCollegue.getRole()).collect(Collectors.toList());
		this.nbRtt = col.getNbRtt();
		this.nbCongesPayes = col.getNbCongesPayes();
		this.departement = col.getDepartement();
	}

	/**
	 * Getter
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Setter
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Getter
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Setter
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Getter
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * Setter
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
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
	 * Getter
	 * @return the departement
	 */
	public Departement getDepartement() {
		return departement;
	}

	/**
	 * Setter
	 * @param departement the departement to set
	 */
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	
	
	
}
