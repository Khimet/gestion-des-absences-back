package dev.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.domain.Collegue;
import dev.domain.JFerieRtt;

/**
 * @author khimet
 *
 */
public interface JFerieRttRepo extends JpaRepository<JFerieRtt, Integer>{
	
	@Query("select j from JFerieRtt j where EXTRACT(MONTH FROM j.date) = ?1 and EXTRACT(YEAR FROM j.date) = ?2")
	List<JFerieRtt> findJourFerieRttMoisAnnee(int mois, int annee);

	@Query("select j.date from JFerieRtt j")
	public List<LocalDate> findAllDate();
	
	@Query("select j from JFerieRtt j where EXTRACT(YEAR FROM j.date) = ?1")
	public List<JFerieRtt> findAllByAnnee(int annee);
}
