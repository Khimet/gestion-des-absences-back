/**
 * 
 */
package dev.domain;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import dev.domain.enumerations.TypeFerieRtt;

/**
 * @author robin
 *
 */
@Table(name="jour_ferie_rtt")
@Entity
public class JFerieRtt {
	
	@Id
	private UUID uuid;
	private LocalDate date;
	
	@Enumerated(EnumType.STRING)
	private TypeFerieRtt type;
	private boolean valide;
	
	private String commentaire;
	
	public JFerieRtt() {
		super();
		this.uuid = UUID.randomUUID();
	}
	
	public JFerieRtt(LocalDate date, TypeFerieRtt type, boolean valide, String commentaire) {
		super();
		this.uuid = UUID.randomUUID();
		this.date = date;
		this.type = type;
		this.valide = valide;
		this.commentaire = commentaire;
	}
	
	/**
	 * Getter
	 * @return the uuid
	 */
	public UUID getUuid() {
		return uuid;
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
