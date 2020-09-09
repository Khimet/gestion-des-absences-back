package dev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import dev.domain.Collegue;
import dev.domain.enumerations.Departement;

public interface CollegueRepo extends JpaRepository<Collegue, Long> {

    Optional<Collegue> findByEmail(String email);
    
    @Query("select c from Collegue c where c.departement = ?1")
	List<Collegue> findCollegueDepartement(Departement depart);
    
    @Query("select DISTINCT c.departement from Collegue c")
    List<Departement> findAllDistinctDepartement();


    @Modifying
    @Query("update Collegue c set c.nbRtt = ?1 where c.id = ?2")
	void setCompteursRttPlus1(int nb, Long id);
    
    @Modifying
    @Query("update Collegue c set c.nbCongesPayes = ?1 where c.id = ?2")
    void setCompteursCongePlus1(int nb, Long id);

    
    @Modifying
	@Query("update Collegue c set c.nbCongesPayes = ?1 where c.id = ?2")
	public void replaceCongePaye(int nb, Long id);
    
    @Modifying
	@Query("update Collegue c set c.nbRtt = ?1 where c.id = ?2")
	public void replaceRtt(int nb, Long id);
    
    @Query("select c from Absence a join a.collegue_abs c where a.status = dev.domain.enumerations.Status.STATUS_INITIAL")
	public List<Collegue> findCollegueAbsStatusInitial();
}
