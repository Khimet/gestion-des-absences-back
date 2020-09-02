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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import dev.domain.enumerations.Status;
import dev.domain.enumerations.Type;

/**
 * @author robin
 *
 */

@Entity
public class Absence {

	@Id
	private UUID uuid;
	
	private LocalDate dateDebut;
	private LocalDate dateFin;
	
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private String motif;
	
	@ManyToOne
	private Collegue collegue_abs;

	public Absence() {
		super();
		this.uuid = UUID.randomUUID();
	}

	public Absence(LocalDate dateDebut, LocalDate dateFin, Type type, Status status, String motif, Collegue collegue_abs) {
		super();
		this.uuid = UUID.randomUUID();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.type = type;
		this.status = status;
		this.motif = motif;
		this.collegue_abs = collegue_abs;
	}

	/**
	 * Getter
	 * @return the uuid
	 */
	public UUID getUuid() {
		return uuid;
	}

	/**
	 * Setter
	 * @param uuid the uuid to set
	 */
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	/**
	 * Getter
	 * @return the dateDebut
	 */
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	/**
	 * Setter
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * Getter
	 * @return the dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**
	 * Setter
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * Getter
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Setter
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * Getter
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Setter
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * Getter
	 * @return the motif
	 */
	public String getMotif() {
		return motif;
	}

	/**
	 * Setter
	 * @param motif the motif to set
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	}

	/** Getter
	 * @return the collegue_abs
	 */
	public Collegue getCollegue_abs() {
		return collegue_abs;
	}

	/** Setter
	 * @param collegue_abs the collegue_abs to set
	 */
	public void setCollegue_abs(Collegue collegue_abs) {
		this.collegue_abs = collegue_abs;
	}
}
