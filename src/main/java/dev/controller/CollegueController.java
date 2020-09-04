package dev.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.CollegueVM;
import dev.domain.Collegue;
import dev.domain.enumerations.Departement;
import dev.repository.CollegueRepo;

/**
 * @author khimet
 *
 */
@RestController
@RequestMapping("collegue")
public class CollegueController {
	
	private CollegueRepo collegueRepo;

	/**
	 * @param collegueRepo
	 */
	public CollegueController(CollegueRepo collegueRepo) {
		super();
		this.collegueRepo = collegueRepo;
	}
	
	@GetMapping
	public ResponseEntity<?> GetCollegueDepartement(
			@RequestParam(name ="departement") Departement departement){
		
		if (departement == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
		}
		
		List<Collegue> collegues = collegueRepo.findCollegueDepartement(departement);
		
		List<CollegueVM> resultat = new ArrayList<>();
		
		collegues.forEach(c -> {
			resultat.add(new CollegueVM(c));
		});
		
		
		
		return ResponseEntity.status(HttpStatus.OK).body(resultat);
		
	}
	

}
