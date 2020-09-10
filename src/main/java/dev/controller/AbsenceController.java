/**
 * 
 */
package dev.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

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

import dev.controller.vm.AbsenceVM;
import dev.controller.vm.ValidationVM;
import dev.controller.vm.AbsenceVMStringDate;
import dev.domain.enumerations.Departement;
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
	public List<AbsenceVMStringDate> getListAbsence() {
		return this.absenceService.findAbsences();	
	}
	
	@DeleteMapping("{uuid}")
	public void supprAbsence(@PathVariable UUID uuid) {
		this.absenceService.deleteAbs(uuid);
	}

	@PostMapping
	public ResponseEntity<?> newAbsence(@RequestBody @Valid AbsenceVMStringDate newAbs, BindingResult res) { 
		return this.absenceService.saveAbs(newAbs);
	}
	
	@GetMapping("vmad")
	public ResponseEntity<?> GetAbsValideeMoisAnneeDepartement(
			@RequestParam("mois") Integer mois,
			@RequestParam("annee") Integer annee,
			@RequestParam("departement") Departement departement){
		
		if ( mois == null || annee == null || departement == null || mois <= 0 || annee <= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
		}
		
		List<AbsenceVM> resultat = absenceService.getAbsencesValideeMoisAnneeDepartement(mois, annee, departement);
		
		
		return ResponseEntity.status(HttpStatus.OK).body(resultat);
	}
	
	@PatchMapping
	public ResponseEntity<?> replaceAbsence(@RequestBody AbsenceVMStringDate updateAbs) {
		return this.absenceService.patchAbs(updateAbs);
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
	
	@Secured("ROLE_MANAGER")
	@GetMapping("par-role")
	public ResponseEntity<?> getListAbsenceParRole(){
		return this.absenceService.getListAbsenceParRole();
	}
	
	@Secured("ROLE_MANAGER")
	@PatchMapping("par-role")
	public ResponseEntity<?> replaceStatusAbs(@RequestBody ValidationVM vvm){
		return this.absenceService.replaceStatusAbs(vvm);
	}
}
