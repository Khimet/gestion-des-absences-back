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
import dev.controller.vm.JFerieRttVM;
import dev.domain.Absence;
import dev.domain.Collegue;
import dev.domain.JFerieRtt;
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

	public AbsencePostVM saveAbs(AbsencePostVM a) {
		Optional<Collegue> col = getColConnecte();
		if (col.isPresent()) {

			Absence tmp = absenceRepo.save(new Absence(a.getDateDebut(), a.getDateFin(), a.getType(),
					Status.STATUS_INITIAL, a.getMotif(), col.get()));
			AbsencePostVM abspost = new AbsencePostVM(tmp.getDateDebut(), tmp.getDateFin(), tmp.getType(),
					tmp.getMotif());
			return abspost;
		}
		throw new RuntimeException("Error col non connecté -  save absence");
	}

	public List<AbsenceVM> findAbsenceMoisAnnee(int mois, int annee) {

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

			List<Absence> absMoisAnneeDepartement = absenceRepo.findAbsencesValideeMoisAnneeDepartement(mois, annee,
					departement);

			List<AbsenceVM> resultat = new ArrayList<>();

			absMoisAnneeDepartement.forEach(a -> {
				resultat.add(new AbsenceVM(a));
			});

			return resultat;

		}

		throw new RuntimeException(
				"Error col non connecté ou vous n'êtes pas un manager et donc vous n'êtes pas autorisé");
	}
}
