/**
 * 
 */
package dev.controller.vm;

import java.time.LocalDate;

import dev.domain.enumerations.TypeFerieRtt;


public class JFerieRttVM {
	
	private int id;
	private LocalDate date;
	private TypeFerieRtt type;
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
	
	public JFerieRttVM(int id, LocalDate date, TypeFerieRtt type, String commentaire) {
		super();
		this.id = id;
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
	public JFerieRttVM(int id, LocalDate date, TypeFerieRtt type, String jour, String commentaire) {
		super();
		this.id = id;
		this.date = date;
		this.type = type;
		this.jour = jour;
		this.commentaire = commentaire;
	}

	/** Getter
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/** Setter
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
