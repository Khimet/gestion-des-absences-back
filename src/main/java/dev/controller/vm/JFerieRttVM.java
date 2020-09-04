/**
 * 
 */
package dev.controller.vm;

import java.time.LocalDate;

import dev.domain.JourFerieRtt;
import dev.domain.enumerations.TypeFerieRtt;

/**
 * @author vokankocak
 *
 */
public class JFerieRttVM {
	
	private LocalDate date;
	private TypeFerieRtt type;
	private String commentaire;
	
	
	
	
	/** Constructeur
	 * @param date
	 * @param type
	 */
	public JFerieRttVM(JourFerieRtt jfr) {
        this.date = jfr.getDate();
        this.type = jfr.getType();
        this.commentaire = jfr.getCommentaire();
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
