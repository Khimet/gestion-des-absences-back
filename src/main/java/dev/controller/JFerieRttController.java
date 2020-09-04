/**
 * 
 */
package dev.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		return jFerieRttService.getListAnnee();
	}
	
	@GetMapping("{annee}")
	public List<JFerieRttVM> getAllJFerieRtt(@PathVariable String annee){
		return jFerieRttService.getListJFerieRtt(annee);
	}
}
