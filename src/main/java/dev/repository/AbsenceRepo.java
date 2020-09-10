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
import dev.controller.vm.ValidationVM;
import dev.domain.Absence;
import dev.domain.Collegue;
import dev.domain.enumerations.Departement;
import dev.domain.enumerations.Status;
import dev.domain.enumerations.Type;

/**
 * Classe AbsenceRepo qui permet de récupérer les donées en BDD.
 * @author robin
 *
 */
public interface AbsenceRepo extends JpaRepository<Absence, UUID>{
	@Query("select a from Absence a where a.status = dev.domain.enumerations.Status.STATUS_VALIDEE and (EXTRACT(MONTH FROM a.dateDebut) = ?1 or  EXTRACT(MONTH FROM a.dateFin)=?1) and EXTRACT(YEAR FROM a.dateDebut) = ?2 ")
    List<Absence>findAbsenceMoisAnnee(int mois, int annee); 

	@Query("select a from Absence a where a.collegue_abs = ?1")
	public List<Absence> findAbsences(Collegue col);
	
	@Query("select a from Absence a where a.collegue_abs.id = ?1 and a.status = dev.domain.enumerations.Status.STATUS_INITIAL")
	public List<Absence> findAbsencesStatusInitial(Long id);
	
	@Modifying
	@Query("delete from Absence a where a.uuid = ?1 and a.collegue_abs = ?2")
	public void deleteAbs(UUID uuid, Collegue col);
	
	@Modifying
	@Query("update Absence a set a.dateDebut = ?1 , a.dateFin = ?2 , a.type = ?3 , a.motif = ?4 , a.status = dev.domain.enumerations.Status.STATUS_INITIAL where a.uuid = ?5 and a.collegue_abs = ?6")
	public void patchAbs(LocalDate newDateDebut, LocalDate newDateFin, Type newType, String newMotif, UUID uuid, Collegue col);

	@Query("select a from Absence a where EXTRACT(MONTH FROM a.dateDebut) = ?1 and EXTRACT(YEAR FROM a.dateDebut) = ?2 and a.status = dev.domain.enumerations.Status.STATUS_VALIDEE and a.collegue_abs.departement = ?3 ORDER BY a.collegue_abs.nom")
	public List<Absence> findAbsencesValideeMoisAnneeDepartement(int mois, int annee, Departement departement);
	
	@Query("select new dev.controller.vm.ValidationVM(a.uuid, a.dateDebut, a.dateFin, a.type, CONCAT(col.nom,' ',col.prenom), a.status) from Absence a JOIN a.collegue_abs col JOIN col.roles r where a.status = dev.domain.enumerations.Status.STATUS_ATTENTE_VALIDATION and"
			+ " a.collegue_abs.departement = ?1 and r.role = 'ROLE_EMPLOYE'")
	public List<ValidationVM> findByRole(Departement departement);

	@Modifying
	@Query("update Absence a set a.status = ?1 where a.uuid = ?2")
	public void replaceStatusAbs(Status s, UUID uuid);

	@Query("select a.collegue_abs from Absence a where a.uuid = ?1")
	public Collegue getColByAbsUuid(UUID uuid);
	
}
