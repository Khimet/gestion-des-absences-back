/**
 * 
 */
package dev.controller.vm;

import java.time.LocalDate;
import java.util.UUID;

import dev.domain.enumerations.TypeFerieRtt;


public class JFerieRttVM {
	
	private UUID uuid;
	private LocalDate date;
	private TypeFerieRtt type;
	private boolean valide;
	private String jour;
	private String commentaire;
	
	/** Constructeur
	 * 
	 */
	public JFerieRttVM() {
		super();
	}
	
	/** Constructeur
	 * @param date
	 * @param type
	 * @param commentaire
	 */
	public JFerieRttVM(LocalDate date, TypeFerieRtt type, String commentaire) {
		super();
		this.date = date;
		this.type = type;
		this.commentaire = commentaire;
	}
	
	public JFerieRttVM(UUID uuid, LocalDate date, TypeFerieRtt type, String commentaire) {
		super();
		this.uuid = uuid;
		this.date = date;
		this.type = type;
		this.commentaire = commentaire;
	}

	/** Constructeur
	 * @param id
	 * @param date
	 * @param type
	 * @param jour
	 * @param commentaire
	 */
	public JFerieRttVM(UUID uuid, LocalDate date, TypeFerieRtt type, boolean valide, String jour, String commentaire) {
		super();
		this.uuid = uuid;
		this.date = date;
		this.type = type;
		this.valide = valide;
		this.jour = jour;
		this.commentaire = commentaire;
	}

	/** Getter
	 * @return the id
	 */
	public UUID getUuid() {
		return uuid;
	}

	/** Setter
	 * @param id the id to set
	 */
	public void setId(UUID uuid) {
		this.uuid = uuid;
	}

	/** Getter
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/** Setter
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/** Getter
	 * @return the type
	 */
	public TypeFerieRtt getType() {
		return type;
	}

	/** Setter
	 * @param type the type to set
	 */
	public void setType(TypeFerieRtt type) {
		this.type = type;
	}

	/**
	 * Getter
	 * @return the valide
	 */
	public boolean isValide() {
		return valide;
	}

	/**
	 * Setter
	 * @param valide the valide to set
	 */
	public void setValide(boolean valide) {
		this.valide = valide;
	}

	/** Getter
	 * @return the jour
	 */
	public String getJour() {
		return jour;
	}

	/** Setter
	 * @param jour the jour to set
	 */
	public void setJour(String jour) {
		this.jour = jour;
	}

	/** Getter
	 * @return the commentaire
	 */
	public String getCommentaire() {
		return commentaire;
	}

	/** Setter
	 * @param commentaire the commentaire to set
	 */
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
}
