/**
 * 
 */
package dev.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.enumerations.Departement;
import dev.repository.CollegueRepo;

/**
 * @author khimet
 *
 */
@RestController
@RequestMapping("departement")
public class DepartementController {
	
	private CollegueRepo collegueRepo;

	/**
	 * @param collegueRepo
	 */
	public DepartementController(CollegueRepo collegueRepo) {
		super();
		this.collegueRepo = collegueRepo;
	}
	
	@GetMapping()
	public ResponseEntity<?> getAllDepartements(){
		
		List<Departement> departements = collegueRepo.findAllDistinctDepartement();
		
		return ResponseEntity.status(HttpStatus.OK).body(departements);
	}
	
	

}
