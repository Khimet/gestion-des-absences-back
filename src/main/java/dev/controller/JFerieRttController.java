package dev.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("jferiesrtt")
public class JFerieRttController {
	
	private JFerieRttService jFerieRttService;

	/**
	 * @param jourFerieRttRepo
	 */
	public JFerieRttController(JFerieRttService jFerieRttService) {
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
	
	@PostMapping
	public ResponseEntity<?> ajoutJFerieRtt(@RequestBody @Valid JFerieRttVM newJFerieRtt, BindingResult res){
		if(res.hasErrors()) {
			throw new RuntimeException("Données incorrects pour post jour ferie et rtt");
		}	
		return this.jFerieRttService.ajoutJFerieRtt(newJFerieRtt);
	}
	
	@Secured("ROLE_ADMINISTRATEUR")
	@DeleteMapping("{uuid}")
	public ResponseEntity<?> deleteJFerieRtt(@PathVariable UUID uuid){
		return this.jFerieRttService.deleteJFerieRtt(uuid);
	}
	
	@Secured("ROLE_ADMINISTRATEUR")
	@PatchMapping
	public ResponseEntity<?> updateJFerieRtt(@RequestBody @Valid JFerieRttVM modifJFerieRtt, BindingResult res){
		if(res.hasErrors()) {
			throw new RuntimeException("Données incorrects pour post jour ferie et rtt");
		}
		return this.jFerieRttService.updateJFerieRtt(modifJFerieRtt);
	}
	
	
	
	
	
	
	

}
