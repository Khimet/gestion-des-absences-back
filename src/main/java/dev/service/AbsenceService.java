/**
 * 
 */
package dev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dev.controller.vm.AbsencePostVM;
import dev.controller.vm.AbsenceVM;
import dev.domain.Absence;
import dev.domain.Collegue;
import dev.domain.enumerations.Status;
import dev.repository.AbsenceRepo;

/**
 * @author robin
 *
 */
@Service
@Transactional
public class AbsenceService {

	private AbsenceRepo absenceRepo;

	public AbsenceService(AbsenceRepo absenceRepo) {
		super();
		this.absenceRepo = absenceRepo;
	}

	public List<AbsenceVM> findAbsences(Collegue col) {
		List<Absence> tmp = this.absenceRepo.findAbsences(col);

		List<AbsenceVM> res = new ArrayList<>();

		for (Absence a : tmp) {
			res.add(new AbsenceVM(a.getUuid(), a.getDateDebut(), a.getDateFin(), a.getType(), a.getStatus(), a.getMotif()));
		}
		return res;
	}
	
	public void deleteAbs(UUID uuid, Collegue col) {
		this.absenceRepo.deleteAbs(uuid, col);
	}
	
	public AbsencePostVM saveAbs(AbsencePostVM a, Collegue col) {
		Absence tmp = absenceRepo.save(new Absence(a.getDateDebut(), a.getDateFin(), a.getType(), Status.STATUS_INITIAL, a.getMotif(), col));
		AbsencePostVM abspost = new AbsencePostVM(tmp.getDateDebut(), tmp.getDateFin(), tmp.getType(), tmp.getMotif());
		return abspost;
	}

}
