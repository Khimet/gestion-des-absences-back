/**
 * 
 */
package dev.controller.vm;

import java.time.LocalDate;
import java.util.UUID;

import dev.domain.enumerations.Status;
import dev.domain.enumerations.Type;

/**
 * @author robin
 *
 */
public class ValidationVM {

	private UUID uuid;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private Type type;
	private String nom;
	private Status status;
	
	public ValidationVM() {
		super();
	}
	
	public ValidationVM(UUID uuid, LocalDate dateDebut, LocalDate dateFin, Type type, String nom, Status status) {
		super();
		this.uuid = uuid;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.type = type;
		this.nom = nom;
		this.status = status;
	}
	/**
	 * Getter
	 * @return the uuidAbs
	 */
	public UUID getUuid() {
		return uuid;
	}
	/**
	 * Setter
	 * @param uuidAbs the uuidAbs to set
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
	
	
	
}
