/**
 * 
 */
package dev.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import dev.domain.Absence;
import dev.domain.Collegue;
import dev.domain.enumerations.Type;

/**
 * @author robin
 *
 */
public interface AbsenceRepo extends JpaRepository<Absence, UUID>{
	@Query("select a from Absence a where a.status = dev.domain.enumerations.Status.STATUS_VALIDEE and (EXTRACT(MONTH FROM a.dateDebut) = ?1 or  EXTRACT(MONTH FROM a.dateFin)=?1) and EXTRACT(YEAR FROM a.dateDebut) = ?2 ")
    List<Absence>findAbsenceMoisAnnee(int mois, int annee); 

	@Query("select a from Absence a where a.collegue_abs = ?1")
	public List<Absence> findAbsences(Collegue col);
	
	@Modifying
	@Query("delete from Absence a where a.uuid = ?1 and a.collegue_abs = ?2")
	public void deleteAbs(UUID uuid, Collegue col);
	
	@Modifying
	@Query("update Absence a set a.dateDebut = ?1 , a.dateFin = ?2 , a.type = ?3 , a.motif = ?4 , a.status = dev.domain.enumerations.Status.STATUS_INITIAL where a.uuid = ?5 and a.collegue_abs = ?6")
	public void patchAbs(LocalDate newDateDebut, LocalDate newDateFin, Type newType, String newMotif, UUID uuid, Collegue col);
}
