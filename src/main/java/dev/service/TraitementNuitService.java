package dev.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dev.domain.Absence;
import dev.domain.Collegue;
import dev.domain.enumerations.Status;
import dev.domain.enumerations.Type;
import dev.repository.AbsenceRepo;
import dev.repository.CollegueRepo;


@Service
@Transactional
public class TraitementNuitService extends LogService {
	
	private AbsenceRepo absenceRepo;

	/** Constructeur
	 * 
	 */
	public TraitementNuitService(AbsenceRepo absenceRepo, CollegueRepo collegueRepo) {
		super(collegueRepo);
		this.absenceRepo = absenceRepo;	
		
	}
	
	public void updatesCompteur() {
		
		Optional<Collegue> col = this.getColConnecte();

		if (col.isPresent()) {
			

			List<Collegue> listCol = this.getColRepo().findCollegueAbsStatusInitial();
			listCol = listCol.stream().distinct().collect(Collectors.toList());
			
			
			for (Collegue collegue : listCol) {
				int compteurRtt = collegue.getNbRtt();
				int compteurConge = collegue.getNbCongesPayes();
				
				List<Absence> listAbsStatusInitial = this.absenceRepo.findAbsencesStatusInitial(collegue.getId());
				
				for (Absence abs : listAbsStatusInitial) {

					if (abs.getType().equals(Type.TYPE_RTT)) {
						if ((abs.getCollegue_abs().getNbRtt()) > 0 ) {						
							this.absenceRepo.replaceStatusAbs(Status.STATUS_ATTENTE_VALIDATION, abs.getUuid());
							compteurRtt--;
							this.getColRepo().replaceRtt(compteurRtt, abs.getCollegue_abs().getId());
							
						}else {
							this.absenceRepo.replaceStatusAbs(Status.STATUS_REJETEE, abs.getUuid());
							
						}
					}
					
					if ((abs.getType().equals(Type.TYPE_CONGE_PAYE) || abs.getType().equals(Type.TYPE_CONGE_SANS_SOLDE))) {
						if ((abs.getCollegue_abs().getNbCongesPayes()) > 0 ) {
							
							this.absenceRepo.replaceStatusAbs(Status.STATUS_ATTENTE_VALIDATION, abs.getUuid());
							compteurConge--;
							this.getColRepo().replaceCongePaye(compteurConge, abs.getCollegue_abs().getId());
						}
						else {
							this.absenceRepo.replaceStatusAbs(Status.STATUS_REJETEE, abs.getUuid());	
						}
					}
				}
			}
		}	
	}
}
