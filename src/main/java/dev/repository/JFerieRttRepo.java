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

import dev.domain.JFerieRtt;

/**
 * @author vokankocak
 *
 */
public interface JFerieRttRepo extends JpaRepository<JFerieRtt, Integer>{
	
	@Query("select j from JFerieRtt j where EXTRACT (MONTH FROM j.date) = ?1 and EXTRACT (YEAR FROM j.date)=?2")
	List<JFerieRtt> findJourFerieRttMoisAnnee(int mois, int annee);
	
	@Query("select j.date from JFerieRtt j")
	public List<LocalDate> findAllDate();
	
	@Query("select j from JFerieRtt j where EXTRACT(YEAR FROM j.date) = ?1")
	public List<JFerieRtt> findAllByAnnee(int annee);
	
	@Query("select j.type from JFerieRtt j")
	public List<String> findAllType();
	
	@Modifying
	@Query("delete from JFerieRtt j where j.uuid = ?1")
	public void deleteByUuid(UUID uuid);
}
