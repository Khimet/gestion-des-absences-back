/**
 * 
 */
package dev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
@Service
@Transactional
public class AbsenceService extends LogService {

	private AbsenceRepo absenceRepo;

	public AbsenceService(AbsenceRepo absenceRepo, CollegueRepo collegueRepo) {
		super(collegueRepo);
		this.absenceRepo = absenceRepo;
	}

	public List<AbsenceVM> findAbsences() {

		Optional<Collegue> col = this.getColConnecte();

		if (col.isPresent()) {

			List<Absence> tmp = this.absenceRepo.findAbsences(col.get());
			List<AbsenceVM> res = new ArrayList<>();

			for (Absence a : tmp) {
				res.add(new AbsenceVM(a.getUuid(), a.getDateDebut(), a.getDateFin(), a.getType(), a.getStatus(),
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
	
	public ResponseEntity<?> putAbs(UUID uuid) {
		Optional<Collegue> col = getColConnecte();
		if (col.isPresent()) {
			Optional<Absence> absenceOpt = absenceRepo.findById(uuid);
			
			Absence updateAbs = new Absence();
			
			updateAbs.setDateDebut(absenceOpt.get().getDateDebut());
			updateAbs.setDateFin(absenceOpt.get().getDateFin());
			updateAbs.setType(absenceOpt.get().getType());
			updateAbs.setMotif(absenceOpt.get().getMotif());
			updateAbs.setStatus(Status.STATUS_INITIAL);
						
			Absence tmp = absenceRepo.save(updateAbs);
			AbsencePostVM absUp = new AbsencePostVM(tmp.getDateDebut(), tmp.getDateFin(), tmp.getType(),
					tmp.getMotif());
			
			return ResponseEntity.status(HttpStatus.OK).body(absUp);
					

		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("erreur");
		
		
	}
	public ResponseEntity<?> saveAbs(AbsencePostVM absenceNew) {
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
				AbsencePostVM abspost = new AbsencePostVM(tmp.getDateDebut(), tmp.getDateFin(), tmp.getType(),
						tmp.getMotif());

				return ResponseEntity.status(HttpStatus.OK).body(abspost);
			}

		}		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dates se chevauchent");
	}

	public List<AbsenceVM> findAbsenceMoisAnnee(int mois, int annee){
		
		List<Absence> tmp = this.absenceRepo.findAbsenceMoisAnnee(mois, annee);
		List<AbsenceVM> resultatsAbs = new ArrayList<>();
     
	    tmp.forEach(abs -> {
	         resultatsAbs.add(new AbsenceVM(abs));
	    });
	    return resultatsAbs;
	}
}
