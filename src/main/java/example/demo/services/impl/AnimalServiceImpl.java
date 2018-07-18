package example.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.demo.domain.Animal;
import example.demo.repository.AnimalRepository;
import example.demo.services.AnimalService;

@Service
public class AnimalServiceImpl implements AnimalService{

	@Autowired
	private AnimalRepository animalRepository;
	
	@Override
	public Animal saveAnimal(String name, boolean genere) {
		
		Animal animal = new Animal();
		animal.setName(name);
		animal.setGenere(genere);
		
		return animalRepository.save(animal);
		 
	}
	
	

}
