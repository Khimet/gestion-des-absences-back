/**
 * 
 */
package dev.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.AbsenceVMStringDate;
import dev.repository.AbsenceRepo;
import dev.service.AbsenceService;
import dev.service.TraitementNuitService;

/**
 * @author eltahhansana
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("traitementdenuit")
public class TraitementNuitController {
	
	private TraitementNuitService trNuitService;
	private AbsenceService absenceService;

	/** Constructeur
	 * 
	 */
	public TraitementNuitController(TraitementNuitService traitNuitServ, AbsenceService absenceRepo) {
		super();
		this.trNuitService = traitNuitServ;
		this.absenceService = absenceRepo;
		
		
	}
	
	@GetMapping
	public void getTraitementNuit() {
		this.trNuitService.updatesCompteur();
	}

}
