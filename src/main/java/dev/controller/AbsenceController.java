/**
 * 
 */
package dev.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.AbsencePostVM;
import dev.controller.vm.AbsenceVM;
import dev.domain.Absence;
import dev.domain.Collegue;
import dev.domain.enumerations.Status;
import dev.repository.AbsenceRepo;
import dev.repository.CollegueRepo;

/**
 * @author robin
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("absences")
public class AbsenceController {

	private AbsenceRepo absenceRepo;
	private CollegueRepo collegueRepo;


	/** Constructeur
	 * @param absenceRepo
	 * @param collegueRepo
	 */
	public AbsenceController(AbsenceRepo absenceRepo, CollegueRepo collegueRepo) {
		super();
		this.absenceRepo = absenceRepo;
		this.collegueRepo = collegueRepo;
	}
	
	public Optional<Collegue> getColConnecte() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return this.collegueRepo.findByEmail(email);
	}

	@GetMapping
	public List<AbsenceVM> getListAbsence() {

		Optional<Collegue> col = this.getColConnecte();

		List<Absence> tmp = this.absenceRepo.findAbsences(col.get());
		List<AbsenceVM> res = new ArrayList<>();

		for (Absence a : tmp) {
			res.add(new AbsenceVM(a.getUuid(), a.getDateDebut(), a.getDateFin(), a.getType(), a.getStatus()));
		}
		return res;
	}
	
	@DeleteMapping("{uuid}")
	public void supprAbsence(@PathVariable UUID uuid) {
		
		Optional<Collegue> col = this.getColConnecte();
		this.absenceRepo.deleteAbs(uuid, col.get());
	}



	@PostMapping
	public AbsencePostVM newAbsence(@RequestBody @Valid AbsencePostVM newAbsence, BindingResult res) {
		if(res.hasErrors()) {
			System.out.println("error");
		}
		Collegue col = getColConnecte().get();
		
		Absence tmp = absenceRepo.save(new Absence(newAbsence.getDateDebut(), newAbsence.getDateFin(), newAbsence.getType(), Status.STATUS_INITIAL, newAbsence.getMotif(), col));
		AbsencePostVM abspost = new AbsencePostVM(tmp.getDateDebut(), tmp.getDateFin(), tmp.getType(), tmp.getMotif());
		return abspost;
	}
}
