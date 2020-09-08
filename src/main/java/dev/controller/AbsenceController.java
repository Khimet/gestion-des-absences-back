/**
 * 
 */
package dev.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.AbsencePostVM;
import dev.controller.vm.AbsenceVM;
import dev.service.AbsenceService;

/**
 * @author robin
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("absences")
public class AbsenceController {

	private AbsenceService absenceService;

	/**
	 * Constructeur
	 * 
	 * @param absenceRepo
	 */
	public AbsenceController(AbsenceService absenceRepo) {
		super();
		this.absenceService = absenceRepo;
	}
	
	@GetMapping
	public List<AbsenceVM> getListAbsence() {
		return this.absenceService.findAbsences();	
	}
	
	@DeleteMapping("{uuid}")
	public void supprAbsence(@PathVariable UUID uuid) {
		this.absenceService.deleteAbs(uuid);
	}

	@PostMapping
	public AbsencePostVM newAbsence(@RequestBody @Valid AbsencePostVM newAbs, BindingResult res) {
		if(res.hasErrors()) {
			throw new RuntimeException("Données incorrects pour post absence");
		}	
		return this.absenceService.saveAbs(newAbs);
	}
	
	@GetMapping("ma")
    public ResponseEntity<?> getListAbsenceMoisAnnee(
            @RequestParam("mois") Integer mois,
            @RequestParam("annee") Integer annee){
        
        if ( mois == null || annee == null || mois < 0 || annee <= 0 ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
        
        List<AbsenceVM> absence = absenceService.findAbsenceMoisAnnee(mois, annee);
       
        return ResponseEntity.status(HttpStatus.OK).body(absence);
    }
	
	// TODO
	// @GetMapping("par-role")
	// public ResponseEntity<?> getListAbsenceParRole(){}
}
