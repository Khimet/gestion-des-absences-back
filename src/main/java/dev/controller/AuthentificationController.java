package dev.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.CollegueVM;
import dev.domain.Collegue;
import dev.repository.CollegueRepo;

/**
 * WEB API d'authentification.
 *
 * Elle permet de récupérer les informations du collègue connecté.
 */
@RestController
public class AuthentificationController {

    private CollegueRepo collegueRepo;

    public AuthentificationController(CollegueRepo collegueRepo) {
        this.collegueRepo = collegueRepo;
    }

    @GetMapping("/me")
    public ResponseEntity<?> quiSuisJe() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return this.collegueRepo.findByEmail(email)
                .map(CollegueVM::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
    
    @GetMapping("/test")
    public List<Long> testBdd() {
    	List<Collegue> listCol = this.collegueRepo.findAll();
    	List<Long> listInt = new ArrayList<>();
    	for (Collegue col : listCol) {
    		listInt.add(col.getId());
    	}
    	return listInt;
    }
}
