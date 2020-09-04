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
import java.util.stream.Collectors;

import javax.transaction.Transactional;

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
public class JFerieRttService extends LogService{
	
	private JFerieRttRepo jFerieRttRepo;

	/** Constructeur
	 * @param collegueRepo
	 */
	public JFerieRttService(JFerieRttRepo jFerieRttRepo ,CollegueRepo collegueRepo) {
		super(collegueRepo);
		this.jFerieRttRepo = jFerieRttRepo;
	}
	
	public List<String> getListAnnee() {
		Optional<Collegue> col = this.getColConnecte();
		
		if (col.isPresent()) {
			List<LocalDate> tmp = this.jFerieRttRepo.findAllDate();
			List<Integer> listAnnee = tmp.stream().map(v -> v.getYear()).distinct().sorted((f1, f2) -> Integer.compare(f2, f1)).collect(Collectors.toList());
			return listAnnee.stream().map(v -> v.toString()).collect(Collectors.toList());
			
		}
		throw new RuntimeException("Error col non connecté - getListAnnee");
	}
	
	public List<JFerieRttVM> getListJFerieRtt(String annee){
		List<JFerieRtt> tmp = this.jFerieRttRepo.findAllByAnnee(Integer.parseInt(annee));
		List<JFerieRttVM> listJFerieRttVM = new ArrayList<>();
		for(JFerieRtt j : tmp) {
			
			listJFerieRttVM.add(new JFerieRttVM(j.getId(), j.getDate(), j.getType(), j.getDate().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.FRANCE).toString(), j.getCommentaire()));
		}
		return listJFerieRttVM;
	}
}
