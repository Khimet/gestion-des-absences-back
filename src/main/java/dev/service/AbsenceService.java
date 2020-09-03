/**
 * 
 */
package dev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

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
public class AbsenceService extends LogService{

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
		throw new RuntimeException("Error find absence");
	}

	public void deleteAbs(UUID uuid) {
		Optional<Collegue> col = this.getColConnecte();
		if (col.isPresent()) {
			this.absenceRepo.deleteAbs(uuid, col.get());
		} else {
			throw new RuntimeException("Error delete absence");
		}
	}

	public AbsencePostVM saveAbs(AbsencePostVM a) {
		Optional<Collegue> col = getColConnecte();
		if (col.isPresent()) {

			Absence tmp = absenceRepo.save(new Absence(a.getDateDebut(), a.getDateFin(), a.getType(),
					Status.STATUS_INITIAL, a.getMotif(), col.get()));
			AbsencePostVM abspost = new AbsencePostVM(tmp.getDateDebut(), tmp.getDateFin(), tmp.getType(),
					tmp.getMotif());
			return abspost;
		}
		throw new RuntimeException("Error save absence");
	}
}
