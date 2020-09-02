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

/**
 * @author robin
 *
 */
public interface AbsenceRepo extends JpaRepository<Absence, UUID>{

	@Query("select a from Absence a where a.collegue_abs = ?1")
	public List<Absence> findAbsences(Collegue col);
	
	@Transactional
	@Modifying
	@Query("delete from Absence a where a.uuid = ?1 and a.collegue_abs = ?2")
	public void deleteAbs(UUID uuid, Collegue col);
}
