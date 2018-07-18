package example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import example.demo.domain.Animal;
import example.demo.domain.MaxMin;
import example.demo.hacker.rank.services.impl.MiniMaxSum;

@RestController
public class MiniMaxSumController {
	
	@Autowired
	MiniMaxSum miniMaxSum;
	
	@RequestMapping(value = "/maxmin", method = RequestMethod.POST) /// todo este metodo es un endpoint	
	public @ResponseBody MaxMin maxMin(@RequestBody MaxMin param) {		
		return miniMaxSum.maxMin(param.getArr());
	}

}
