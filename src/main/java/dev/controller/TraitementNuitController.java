/**
 * 
 */
package dev.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.service.AbsenceService;
import dev.service.TraitementNuitService;

/**
 * Classe controller pour le traitement de nuit.
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
	 * @param traitNuitServ
	 * @param absenceRepo
	 */
	public TraitementNuitController(TraitementNuitService traitNuitServ, AbsenceService absenceRepo) {
		super();
		this.trNuitService = traitNuitServ;
		this.absenceService = absenceRepo;		
	}
	
	// Appel à la methode su service qui s'occupe du traitement de nuit
	@GetMapping
	public void getTraitementNuit() {
		this.trNuitService.updatesCompteur();
	}

}
