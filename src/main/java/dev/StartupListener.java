package dev;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.domain.Collegue;
import dev.domain.RoleCollegue;
import dev.domain.enumerations.Departement;
import dev.domain.enumerations.Role;
import dev.repository.CollegueRepo;

/**
 * Code de démarrage de l'application.
 * Insertion de jeux de données.
 */
@Component
public class StartupListener {

    private PasswordEncoder passwordEncoder;
    private CollegueRepo collegueRepo;

    public StartupListener(@Value("${app.version}") String appVersion, PasswordEncoder passwordEncoder, CollegueRepo collegueRepo) {
        this.passwordEncoder = passwordEncoder;
        this.collegueRepo = collegueRepo;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onStart() {

        // Création de deux utilisateurs

//        Collegue col1 = new Collegue();
//        col1.setNom("Admin");
//        col1.setPrenom("DEV");
//        col1.setEmail("admin@dev.fr");
//        col1.setMotDePasse(passwordEncoder.encode("superpass"));
//        col1.setRoles(Arrays.asList(new RoleCollegue(col1, Role.ROLE_ADMINISTRATEUR), new RoleCollegue(col1, Role.ROLE_UTILISATEUR)));
//        this.collegueRepo.save(col1);
//  
//        Collegue col2 = new Collegue();

    }
}
