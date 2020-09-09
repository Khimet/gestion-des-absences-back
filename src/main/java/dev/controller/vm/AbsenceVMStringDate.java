/**
 * 
 */
package dev.controller.vm;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import dev.domain.Absence;
import dev.domain.enumerations.Status;
import dev.domain.enumerations.Type;

/**
 * @author robin
 *
 */
public class AbsenceVMStringDate {

	private UUID uuid;
	private String dateDebut;
	private String dateFin;
	private Type type;
	private Status status;
	private String motif;
	
	public AbsenceVMStringDate() {
		super();
	}

	
	public AbsenceVMStringDate(UUID uuid, LocalDate dateDebut, LocalDate dateFin, Type type, Status status, String motif) {
		super();
		DateTimeFormatter formateur = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.uuid = uuid;
		this.dateDebut = formateur.format(dateDebut);
		this.dateFin = formateur.format(dateFin);
		this.type = type;
		this.status = status;
		this.motif = motif;
	}
	
	/**
	 * Constructeur
	 * 
	 * @param dateDebut
	 * @param dateFin
	 * @param type
	 * @param motif
	 */
	public AbsenceVMStringDate(LocalDate dateDebut, LocalDate dateFin, Type type, String motif) {
		super();
		DateTimeFormatter formateur = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.dateDebut = formateur.format(dateDebut);
		this.dateFin = formateur.format(dateDebut);
		this.type = type;
		this.motif = motif;
	}

	public AbsenceVMStringDate(Absence abs) {
		DateTimeFormatter formateur = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		this.dateDebut = formateur.format(abs.getDateDebut());
		this.dateFin = formateur.format(abs.getDateFin());
		this.type = abs.getType();
		this.status = abs.getStatus();
	}


	/** Getter
	 * @return the motif
	 */
	public String getMotif() {
		return motif;
	}

	/** Setter
	 * @param motif the motif to set
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	}

	/**
	 * Getter
	 * @return the dateDebut
	 */
	public String getDateDebut() {
		return dateDebut;
	}

	/**
	 * Setter
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * Getter
	 * @return the dateFin
	 */
	public String getDateFin() {
		return dateFin;
	}

	/**
	 * Setter
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(String dateFin) {
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
}
