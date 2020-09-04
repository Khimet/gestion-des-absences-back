/**
 * 
 */
package dev.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.domain.Absence;

/**
 * @author robin
 *
 */
public interface AbsenceRepo extends JpaRepository<Absence, UUID>{
	@Query("select a from Absence a where a.status = dev.domain.enumerations.Status.STATUS_VALIDEE and (EXTRACT(MONTH FROM a.dateDebut) = ?1 or  EXTRACT(MONTH FROM a.dateFin)=?1) and EXTRACT(YEAR FROM a.dateDebut) = ?2 ")
    List<Absence>findAbsenceMoisAnnee(int mois, int annee); 

}
