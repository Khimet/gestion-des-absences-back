/**
 * 
 */
package dev.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.domain.JourFerieRtt;

/**
 * @author vokankocak
 *
 */
public interface JFerieRttRepo extends JpaRepository<JourFerieRtt, Integer>{
	
	@Query("select j from JourFerieRtt j where EXTRACT (MONTH FROM j.date) = ?1 and EXTRACT (YEAR FROM j.date)=?2")
	List<JourFerieRtt> findJourFerieRttMoisAnnee(int mois, int annee);
}
