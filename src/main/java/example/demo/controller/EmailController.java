package example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import example.demo.domain.EmailDto;
import example.demo.services.EmailService;

@RestController
public class EmailController {

	@Autowired
	EmailService emailService;
	
	@RequestMapping(value = "/mail", method = RequestMethod.GET)
	public @ResponseBody List<EmailDto> getmail() {
		
		return emailService.receiveMail();

	}
	/*
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	private GetProductDto getProduct(@PathVariable Integer id) {
		Long long1 = new Long(id);
		return productService.getProduct(long1);
	}

	*/
	
}
