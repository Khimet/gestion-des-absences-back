package dev.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.JFerieRttVM;
import dev.domain.JFerieRtt;
import dev.repository.JFerieRttRepo;
import dev.service.JFerieRttService;

/**
 * @author khimet
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("jferiestt")
public class JFerieRttController {
	
	private JFerieRttService jFerieRttService;

	/**
	 * @param jourFerieRttRepo
	 */
	public JFerieRttController(JFerieRttService jourFerieRttService) {
		super();
		this.jFerieRttService = jFerieRttService;
	}
	
	@GetMapping
	public ResponseEntity<?> GetJourFerieRttMoisAnnee(
			@RequestParam("mois") Integer mois,
			@RequestParam("annee") Integer annee){
		
		if ( mois == null || annee == null || mois < 0 || annee <= 0 ) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
		}
		
		List<JFerieRttVM> resultat = jFerieRttService.getJFerieRttMoisAnnee(mois, annee);
		
		
		return ResponseEntity.status(HttpStatus.OK).body(resultat);
		
	}
	
	@GetMapping("annees")
	public List<String> getAllAnnee(){
		return jFerieRttService.getListAnnee();
	}
	
	@GetMapping("{annee}")
	public List<JFerieRttVM> getAllJFerieRtt(@PathVariable String annee){
		return jFerieRttService.getListJFerieRtt(annee);

	}
	
	
	
	
	
	

}
