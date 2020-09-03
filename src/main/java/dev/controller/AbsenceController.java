/**
 * 
 */
package dev.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.AbsenceVM;
import dev.domain.Absence;
import dev.repository.AbsenceRepo;

/**
 * @author robin
 *
 */
@RestController
public class AbsenceController {

	private AbsenceRepo absenceRepo;

	public AbsenceController(AbsenceRepo absenceRepo) {
		super();
		this.absenceRepo = absenceRepo;
	}
	
	@GetMapping("/absences")
	public List<AbsenceVM> getListAbsence() {
		List<Absence> tmp = this.absenceRepo.findAll();
		List<AbsenceVM> res = new ArrayList<>();
		
		for(Absence a : tmp) {
			res.add(new AbsenceVM(a.getDateDebut(), a.getDateFin(), a.getType(), a.getStatus()));
		}
		return res;
	}
	@GetMapping
    public ResponseEntity<?> GetJourFerieRttMoisAnnee(
            @RequestParam("mois") Integer mois,
            @RequestParam("annee") Integer annee){
        
        if ( mois == null || annee == null || mois < 0 || annee <= 0 ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
        
        List<Absence> absence = absenceRepo.findAbsenceMoisAnnee(mois, annee);
        
        List<AbsenceVM> resultatsAbs = new ArrayList<>();
        
        absence.forEach(j -> {
            resultatsAbs.add(new AbsenceVM(j));
        });
        
        return ResponseEntity.status(HttpStatus.OK).body(resultatsAbs);
        
    }
	
	
}
