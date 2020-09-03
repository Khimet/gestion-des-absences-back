/**
 * 
 */
package dev.controller;

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
import dev.domain.Collegue;
import dev.repository.CollegueRepo;
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
	private CollegueRepo collegueRepo;

	/**
	 * Constructeur
	 * 
	 * @param absenceRepo
	 * @param collegueRepo
	 */
	public AbsenceController(AbsenceService absenceRepo, CollegueRepo collegueRepo) {
		super();
		this.absenceService = absenceRepo;
		this.collegueRepo = collegueRepo;
	}

	public Optional<Collegue> getColConnecte() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return this.collegueRepo.findByEmail(email);
	}

	@GetMapping
	public List<AbsenceVM> getListAbsence() {

		Optional<Collegue> col = this.getColConnecte();
		return this.absenceService.findAbsences(col.get());
	}

	@DeleteMapping("{uuid}")
	public void supprAbsence(@PathVariable UUID uuid) {

		Optional<Collegue> col = this.getColConnecte();
		this.absenceService.deleteAbs(uuid, col.get());
	}

	@PostMapping
	public AbsencePostVM newAbsence(@RequestBody @Valid AbsencePostVM newAbs, BindingResult res) {
		if(res.hasErrors()) {
			System.out.println("error");
		}
		Collegue col = getColConnecte().get();
		
		 return this.absenceService.saveAbs(newAbs, col);
	}
}
