package dev.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.JFerieRttVM;
import dev.service.JFerieRttService;

/**
 * @author robin
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("jferiesrtt")
public class JFerieRttController {
	
	private JFerieRttService jFerieRttService;

	public JFerieRttController(JFerieRttService jFerieRttService) {
		super();
		this.jFerieRttService = jFerieRttService;
	}

	@GetMapping("annees")
	public List<String> getAllAnnee(){
		return this.jFerieRttService.getListAnnee();
	}
	
	@GetMapping("{annee}")
	public List<JFerieRttVM> getAllJFerieRtt(@PathVariable String annee){
		return this.jFerieRttService.getListJFerieRtt(annee);
	}
	
	/*
	@GetMapping("type")
	public ResponseEntity<?> getAllType(){
		return this.jFerieRttService.getListType();
	}
	*/
	
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
}
