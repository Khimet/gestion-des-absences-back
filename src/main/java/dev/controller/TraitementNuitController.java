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

	/** Constructeur
	 * 
	 */
	public TraitementNuitController() {
		
		
		
	}
	
	/*@GetMapping
	public void getTraitementNuit() {
		return this.trNuitService.updatesCompteur();
	}*/

}
