/**
 * 
 */
package dev.service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.controller.vm.JFerieRttVM;
import dev.domain.Collegue;
import dev.domain.JFerieRtt;
import dev.repository.CollegueRepo;
import dev.repository.JFerieRttRepo;

/**
 * @author robin
 *
 */
@Service
@Transactional
public class JFerieRttService extends LogService {

	private JFerieRttRepo jFerieRttRepo;

	/**
	 * Constructeur
	 * 
	 * @param collegueRepo
	 */
	public JFerieRttService(JFerieRttRepo jFerieRttRepo, CollegueRepo collegueRepo) {
		super(collegueRepo);
		this.jFerieRttRepo = jFerieRttRepo;
	}

	public List<String> getListAnnee() {
		Optional<Collegue> col = this.getColConnecte();

		if (col.isPresent()) {
			List<LocalDate> tmp = this.jFerieRttRepo.findAllDate();
			List<Integer> listAnnee = tmp.stream().map(v -> v.getYear()).distinct()
					.sorted((f1, f2) -> Integer.compare(f2, f1)).collect(Collectors.toList());
			return listAnnee.stream().map(v -> v.toString()).collect(Collectors.toList());

		}
		throw new RuntimeException("Error col non connecté - getListAnnee");
	}

	public List<JFerieRttVM> getListJFerieRtt(String annee) {
		List<JFerieRtt> tmp = this.jFerieRttRepo.findAllByAnnee(Integer.parseInt(annee));
		List<JFerieRttVM> listJFerieRttVM = new ArrayList<>();
		for (JFerieRtt j : tmp) {

			listJFerieRttVM.add(new JFerieRttVM(j.getUuid(), j.getDate(), j.getType(), j.isValide() ,
					j.getDate().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.FRANCE), j.getCommentaire()));
		}
		return listJFerieRttVM;
	}

	/* ne sert plus mais je garde ça de côté
	public ResponseEntity<?> getListType() {
		List<String> tmp = this.jFerieRttRepo.findAllType();
		List<String> res = new ArrayList<>();
		if (!tmp.isEmpty()) {
			res = tmp.stream().distinct().collect(Collectors.toList());
		}
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	*/

	public ResponseEntity<?> ajoutJFerieRtt(@Valid JFerieRttVM newJFerieRtt) {

		if (newJFerieRtt == null || newJFerieRtt.getDate() == null || newJFerieRtt.getType() == null
				|| newJFerieRtt.getCommentaire() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("erreur, valeurs incorrectes");
		}

		List<JFerieRtt> tmp = this.jFerieRttRepo.findAll();
		boolean existeDeja = false;

		for (JFerieRtt j : tmp) {
			if (newJFerieRtt.getDate() == j.getDate()) {
				existeDeja = true;
			}
		}

		if (!existeDeja) {
			JFerieRtt jFerieRtt = this.jFerieRttRepo.save(new JFerieRtt(newJFerieRtt.getDate(), newJFerieRtt.getType(),
					newJFerieRtt.isValide(), newJFerieRtt.getCommentaire()));

			JFerieRttVM res = new JFerieRttVM(jFerieRtt.getUuid(), jFerieRtt.getDate(), jFerieRtt.getType(),
					jFerieRtt.getCommentaire());
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ce jour existe déjà dans la bdd");
	}
	
	public ResponseEntity<?> deleteJFerieRtt(UUID uuid){
		this.jFerieRttRepo.deleteByUuid(uuid);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
}
