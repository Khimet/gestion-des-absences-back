/**
 * 
 */
package dev.service;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;

import dev.domain.Collegue;
import dev.repository.CollegueRepo;

/**
 * @author robin
 *
 */
public abstract class LogService {
	
	private CollegueRepo collegueRepo;
	
	public LogService(CollegueRepo collegueRepo) {
		super();
		this.collegueRepo = collegueRepo;
	}

	public Optional<Collegue> getColConnecte() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return this.collegueRepo.findByEmail(email);
	}
}
