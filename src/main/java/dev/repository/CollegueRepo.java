package dev.repository;

import dev.domain.Collegue;
import dev.domain.enumerations.Departement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CollegueRepo extends JpaRepository<Collegue, Long> {

    Optional<Collegue> findByEmail(String email);
    
    @Query("select c from Collegue c where c.departement = ?1")
	List<Collegue> findCollegueDepartement(Departement depart);
}
