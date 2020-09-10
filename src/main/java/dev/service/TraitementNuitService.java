package dev.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dev.domain.Absence;
import dev.domain.Collegue;
import dev.domain.JFerieRtt;
import dev.domain.enumerations.Status;
import dev.domain.enumerations.Type;
import dev.repository.AbsenceRepo;
import dev.repository.CollegueRepo;
import dev.repository.JFerieRttRepo;

/**
 * Classe qui a pour but de simuler le traitement de nuit.
 * 
 * @author eltahhansana
 *
 */
@Service
@Transactional
public class TraitementNuitService extends LogService {

	private AbsenceRepo absenceRepo;
	private JFerieRttRepo jFerieRttRepo;

	
	/** Constructeur
	 * @param absenceRepo
	 * @param collegueRepo
	 */
	public TraitementNuitService(AbsenceRepo absenceRepo, CollegueRepo collegueRepo, JFerieRttRepo jFerieRttRepo) {
		super(collegueRepo);
		this.absenceRepo = absenceRepo;
		this.jFerieRttRepo = jFerieRttRepo;
	}

	/**
	 * Méthode qui permet de mettre à jour les differents compteurs de congés eainsi que leur status. 
	 * 
	 */
	public void updatesCompteur() {

		// recup le collegue connecter
		Optional<Collegue> col = this.getColConnecte();

		if (col.isPresent()) {			
			// Liste de tout les jours ferie rtt
			List<JFerieRtt> listJFerieRtt = this.jFerieRttRepo.findAllJFerieRtt();
			// Liste de tous les collegue
			
			List<Collegue> listCollegue = this.getColRepo().findAll();
			
			int nbJourRtt = 0;
			
			// Chaque jour ferié non valide passe a vrai et incrementation d'un compteur
			for (JFerieRtt jf : listJFerieRtt) {
					if (!jf.isValide()) {
						jf.setValide(true);
						nbJourRtt++;
					}
			}

			// Pour chaque collegue on lui decemente son compteur de congé rtt
			for (Collegue collegue : listCollegue) {
				//int compteurRtt = collegue.getNbRtt();
				this.getColRepo().replaceRtt(collegue.getNbRtt() - nbJourRtt, collegue.getId());

			}
		

			// Liste de collegue dont le le status de leur absence est a initial
			List<Collegue> listCol = this.getColRepo().findCollegueAbsStatusInitial();
			// Rtirer les doublons et reconvertir en liste
			listCol = listCol.stream().distinct().collect(Collectors.toList());
			
			
			// Pour chaque collegue on met a jour son compteur de congé ainsi que le status de son absence
			for (Collegue collegue : listCol) {
				int compteurRtt = collegue.getNbRtt();
				int compteurConge = collegue.getNbCongesPayes();

				List<Absence> listAbsStatusInitial = this.absenceRepo.findAbsencesStatusInitial(collegue.getId());

				for (Absence abs : listAbsStatusInitial) {

					if (abs.getType().equals(Type.TYPE_RTT)) {
						if ((abs.getCollegue_abs().getNbRtt()) > 0) {
							this.absenceRepo.replaceStatusAbs(Status.STATUS_ATTENTE_VALIDATION, abs.getUuid());
							compteurRtt--;
							this.getColRepo().replaceRtt(compteurRtt, abs.getCollegue_abs().getId());

						} else {
							this.absenceRepo.replaceStatusAbs(Status.STATUS_REJETEE, abs.getUuid());

						}
					}

					if ((abs.getType().equals(Type.TYPE_CONGE_PAYE)
							|| abs.getType().equals(Type.TYPE_CONGE_SANS_SOLDE))) {
						if ((abs.getCollegue_abs().getNbCongesPayes()) > 0) {

							this.absenceRepo.replaceStatusAbs(Status.STATUS_ATTENTE_VALIDATION, abs.getUuid());
							compteurConge--;
							this.getColRepo().replaceCongePaye(compteurConge, abs.getCollegue_abs().getId());
						} else {
							this.absenceRepo.replaceStatusAbs(Status.STATUS_REJETEE, abs.getUuid());
						}
					}
				}
			}
			
		}
	}
}


