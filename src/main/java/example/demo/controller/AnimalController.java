package example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import example.demo.domain.Animal;
import example.demo.services.AnimalService;

@Controller
public class AnimalController {

	@Autowired
	private AnimalService animalService;
	
	
	@RequestMapping(value = "/animal", method = RequestMethod.POST) /// todo este metodo es un endpoint	
	public @ResponseBody Animal saveAnimal(@RequestBody Animal animal) {		
		return animalService.saveAnimal(animal.getName(), animal.isGenere());
	}

}
