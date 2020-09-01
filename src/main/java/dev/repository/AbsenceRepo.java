/**
 * 
 */
package dev.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Absence;

/**
 * @author robin
 *
 */
public interface AbsenceRepo extends JpaRepository<Absence, UUID>{

}
