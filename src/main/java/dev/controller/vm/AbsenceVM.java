/**
 * 
 */
package dev.controller.vm;

import java.time.LocalDate;
import java.util.UUID;

import dev.domain.Absence;
import dev.domain.enumerations.Status;
import dev.domain.enumerations.Type;

/**
 * @author robin
 *
 */
public class AbsenceVM {

	private UUID uuid;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private Type type;
	private Status status;
	private String motif;
	private CollegueVM collegueVM;
	
	public AbsenceVM() {
		super();
	}

	public AbsenceVM(UUID uuid, LocalDate dateDebut, LocalDate dateFin, Type type, Status status, String motif) {
		super();
		this.uuid = uuid;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.type = type;
		this.status = status;
		this.motif = motif;
	}
	
	
	public AbsenceVM(Absence abs) {
		super();
		this.uuid = abs.getUuid();
		this.dateDebut = abs.getDateDebut();
		this.dateFin = abs.getDateFin();
		this.type = abs.getType();
		this.status = abs.getStatus();
		this.motif = abs.getMotif();
		this.collegueVM = new CollegueVM(abs.getCollegue_abs());
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

	/**
	 * @return the collegueVM
	 */
	public CollegueVM getCollegueVM() {
		return collegueVM;
	}

	/**
	 * @param collegueVM the collegueVM to set
	 */
	public void setCollegueVM(CollegueVM collegueVM) {
		this.collegueVM = collegueVM;
	}
	
	
}
