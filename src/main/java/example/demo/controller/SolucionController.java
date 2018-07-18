package example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import example.demo.domain.Animal;
import example.demo.domain.Solucion;
import example.demo.hacker.rank.services.impl.SolucionService;

@RestController
public class SolucionController {
	
	@Autowired
	SolucionService solucionService;
	
	
	@RequestMapping(value = "/existString", method = RequestMethod.POST) /// todo este metodo es un endpoint	
	public @ResponseBody String existString(@RequestBody Solucion solucion) {		
		return solucionService.existString(solucion);
	}

}
