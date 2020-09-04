package dev.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.JFerieRttVM;
import dev.domain.JFerieRtt;
import dev.repository.JFerieRttRepo;

/**
 * @author khimet
 *
 */
@RestController
@RequestMapping("jourferiertt")
public class JFerieRttController {
	
	private JFerieRttRepo jourFerieRttRepo;

	/**
	 * @param jourFerieRttRepo
	 */
	public JFerieRttController(JFerieRttRepo jourFerieRttRepo) {
		super();
		this.jourFerieRttRepo = jourFerieRttRepo;
	}
	
	@GetMapping
	public ResponseEntity<?> GetJourFerieRttMoisAnnee(
			@RequestParam("mois") Integer mois,
			@RequestParam("annee") Integer annee){
		
		if ( mois == null || annee == null || mois < 0 || annee <= 0 ) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
		}
		
		List<JFerieRtt> joursFeriesRtts = jourFerieRttRepo.findJourFerieRttMoisAnnee(mois, annee);
		
		List<JFerieRttVM> resultats = new ArrayList<>();
		
		joursFeriesRtts.forEach(j -> {
			resultats.add(new JFerieRttVM(j));
		});
		
		return ResponseEntity.status(HttpStatus.OK).body(resultats);
		
	}
	
	
	
	

}
