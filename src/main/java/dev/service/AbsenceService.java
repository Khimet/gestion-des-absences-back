/**
 * 
 */
package dev.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.controller.vm.AbsenceVM;
import dev.controller.vm.ValidationVM;
import dev.controller.vm.AbsenceVMStringDate;
import dev.domain.Absence;
import dev.domain.Collegue;
import dev.domain.enumerations.Departement;
import dev.domain.enumerations.Role;
import dev.domain.enumerations.Status;
import dev.repository.AbsenceRepo;
import dev.repository.CollegueRepo;

/**
 * @author robin
 *
 */
@Service
@Transactional
public class AbsenceService extends LogService {

	private AbsenceRepo absenceRepo;

	public AbsenceService(AbsenceRepo absenceRepo, CollegueRepo collegueRepo) {
		super(collegueRepo);
		this.absenceRepo = absenceRepo;
	}

	public List<AbsenceVMStringDate> findAbsences() {

		Optional<Collegue> col = this.getColConnecte();

		if (col.isPresent()) {

			List<Absence> tmp = this.absenceRepo.findAbsences(col.get());
			List<AbsenceVMStringDate> res = new ArrayList<>();

			for (Absence a : tmp) {
				res.add(new AbsenceVMStringDate(a.getUuid(), a.getDateDebut(), a.getDateFin(), a.getType(), a.getStatus(),
						a.getMotif()));
			}
			return res;
		}
		throw new RuntimeException("Error col non connecté -  find absence");
	}

	public void deleteAbs(UUID uuid) {
		Optional<Collegue> col = this.getColConnecte();
		if (col.isPresent()) {
			this.absenceRepo.deleteAbs(uuid, col.get());
		} else {
			throw new RuntimeException("Error col non connecté - delete absence");
		}
	}

	public ResponseEntity<?> patchAbs(AbsenceVMStringDate updateAbs) {

		Optional<Collegue> col = getColConnecte();
		// TODO: Convertir AbsenceVMStringDate en Absence
		
		LocalDate dateDebut = LocalDate.parse(updateAbs.getDateDebut(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate dateFin = LocalDate.parse(updateAbs.getDateFin(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		
		if (col.isPresent()) {
			
			List<Absence> listOldAbsence = this.absenceRepo.findAbsences(col.get());
			boolean valide = true;

			for (Absence absenceOld : listOldAbsence) {

				if (!(dateFin.isBefore(absenceOld.getDateDebut())
						|| dateDebut.isAfter(absenceOld.getDateFin())) && !(updateAbs.getUuid().equals(absenceOld.getUuid()))) {

					valide = false;
				}
			}
			
			 if (valide) {
				 
				 this.absenceRepo.patchAbs(dateDebut, dateFin, updateAbs.getType(), updateAbs.getMotif(), updateAbs.getUuid(), col.get());
				 return ResponseEntity.status(HttpStatus.OK).body("");	 
			 }
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dates se chevauchent");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Collegue non présent");
		
		
	}
	
	public ResponseEntity<?> saveAbs(AbsenceVM absenceNew) {
		Optional<Collegue> col = getColConnecte();

		if (col.isPresent()) {
			
			List<Absence> listOldAbsence = this.absenceRepo.findAbsences(col.get());
			boolean valide = true;

			for (Absence absenceOld : listOldAbsence) {

				if (!(absenceNew.getDateFin().isBefore(absenceOld.getDateDebut())
						|| absenceNew.getDateDebut().isAfter(absenceOld.getDateFin()))) {

					valide = false;
				}
			}
			
			if (valide) {
				Absence tmp = absenceRepo.save(new Absence(absenceNew.getDateDebut(), absenceNew.getDateFin(),
						absenceNew.getType(), Status.STATUS_INITIAL, absenceNew.getMotif(), col.get()));
				AbsenceVM abspost = new AbsenceVM(tmp.getDateDebut(), tmp.getDateFin(), tmp.getType(),
						tmp.getMotif());

				return ResponseEntity.status(HttpStatus.OK).body(abspost);
			}
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("les dates se chevauchent");

		}		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("collegue non connecté");
	}

	public List<AbsenceVM> findAbsenceMoisAnnee(int mois, int annee){
		
		List<Absence> tmp = this.absenceRepo.findAbsenceMoisAnnee(mois, annee);
		List<AbsenceVM> resultatsAbs = new ArrayList<>();
     
	    tmp.forEach(abs -> {
	         resultatsAbs.add(new AbsenceVM(abs));
	    });
	    return resultatsAbs;
	}

	public List<AbsenceVM> getAbsencesValideeMoisAnneeDepartement(int mois, int annee, Departement departement) {
		
		Optional<Collegue> col = this.getColConnecte();
		
		if (col.isPresent() && col.get().getRoles().get(0).getRole() == Role.ROLE_MANAGER) {

			List<Absence> absMoisAnneeDepartement = absenceRepo.findAbsencesValideeMoisAnneeDepartement(mois, annee, departement);
			List<AbsenceVM> resultat = new ArrayList<>();

			absMoisAnneeDepartement.forEach(a -> {
				resultat.add(new AbsenceVM(a));
			});
			return resultat;

		}
		throw new RuntimeException("Error col non connecté ou vous n'êtes pas un manager et donc vous n'êtes pas autorisé");
	}

	public ResponseEntity<?> getListAbsenceParRole() {
		Optional<Collegue> col = this.getColConnecte();

		if (col.isPresent()) {		
			List<ValidationVM> vm = this.absenceRepo.findByRole(col.get().getDepartement());
			return ResponseEntity.status(HttpStatus.OK).body(vm);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("erreur collegue non connecté");
	}

	public ResponseEntity<?> replaceStatusAbs(ValidationVM vvm) {
		Optional<Collegue> col = this.getColConnecte();

		if (col.isPresent()) {
			this.absenceRepo.replaceStatusAbs(vvm.getStatus(), vvm.getUuid());
			if(vvm.getStatus().equals(Status.STATUS_REJETEE)) {
				Collegue collegue = this.absenceRepo.getColByAbsUuid(vvm.getUuid());
				// TODO rajouter solde à (collegue) un type de (vvl.getType()) 
			}
			return ResponseEntity.status(HttpStatus.OK).body("");
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("erreur collegue non connecté");
	}
}
