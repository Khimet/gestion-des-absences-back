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

    @Modifying
    @Query("update Collegue c set c.nbRtt = ?1 where c.id = ?2")
	void setCompteursRttPlus1(int nb, Long id);
    
    @Modifying
    @Query("update Collegue c set c.nbCongesPayes = ?1 where c.id = ?2")
    void setCompteursCongePlus1(int nb, Long id);
}
