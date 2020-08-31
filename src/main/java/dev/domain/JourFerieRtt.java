/**
 * 
 */
package dev.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import dev.domain.enumerations.TypeFerieRtt;

/**
 * @author robin
 *
 */
@Entity
public class JourFerieRtt {
	
	@Id
	private int id;
	private LocalDate date;
	
	@Enumerated(EnumType.STRING)
	private TypeFerieRtt type;
	private String commentaire;
	
	public JourFerieRtt() {
		super();
	}
	
	public JourFerieRtt(LocalDate date, TypeFerieRtt type, String commentaire) {
		super();
		this.date = date;
		this.type = type;
		this.commentaire = commentaire;
	}
	/**
	 * Getter
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}
	/**
	 * Setter
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	/**
	 * Getter
	 * @return the type
	 */
	public TypeFerieRtt getType() {
		return type;
	}
	/**
	 * Setter
	 * @param type the type to set
	 */
	public void setType(TypeFerieRtt type) {
		this.type = type;
	}
	/**
	 * Getter
	 * @return the commentaire
	 */
	public String getCommentaire() {
		return commentaire;
	}
	/**
	 * Setter
	 * @param commentaire the commentaire to set
	 */
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	


	
}
