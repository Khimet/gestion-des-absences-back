/**
 * 
 */
package dev.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.enumerations.Type;

/**
 * WEB API qui permet de récupérer les enum dans le front.
 * 
 * @author eltahhansana
 *
 */
@RestController
public class EnumController {

	/** Constructeur
	 * 
	 */
	public EnumController() {
	}
	
	@GetMapping("/typeenum")
    public String[] getTypeEnum() {
		System.out.println("==================================================================================================");
		Type[] tabType= Type.values();
		List<String> tabTypeString = new ArrayList<>() ;
		for (Type type : tabType) {
			tabTypeString.add(type.toString());		
		}
		return tabTypeString.toArray(new String[0]);
		
    }

}
