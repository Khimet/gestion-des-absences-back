/**
 * 
 */
package dev.repository;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import dev.domain.Absence;
import dev.domain.Collegue;
import dev.domain.enumerations.Departement;

/**
 * @author robin
 *
 */
public interface AbsenceRepo extends JpaRepository<Absence, UUID>{
	@Query("select a from Absence a where a.status = dev.domain.enumerations.Status.STATUS_VALIDEE and (EXTRACT(MONTH FROM a.dateDebut) = ?1 or  EXTRACT(MONTH FROM a.dateFin)=?1) and EXTRACT(YEAR FROM a.dateDebut) = ?2 ")
    List<Absence>findAbsenceMoisAnnee(int mois, int annee); 

	@Query("select a from Absence a where a.collegue_abs = ?1")
	public List<Absence> findAbsences(Collegue col);
	
	@Transactional
	@Modifying
	@Query("delete from Absence a where a.uuid = ?1 and a.collegue_abs = ?2")
	public void deleteAbs(UUID uuid, Collegue col);
	
	@Query("select a from Absence a where EXTRACT(MONTH FROM a.dateDebut) = ?1 and EXTRACT(YEAR FROM a.dateDebut) = ?2 and a.status = dev.domain.enumerations.Status.STATUS_VALIDEE and a.collegue_abs.departement = ?3 ORDER BY a.collegue_abs.nom")
	public List<Absence> findAbsencesValideeMoisAnneeDepartement(int mois, int annee, Departement departement);
	
}
