package com.movie.api.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.movie.api.dao.MovieDao;
import com.movie.api.entity.MovieEntity;
import com.movie.api.repository.MovieRepository;

@Controller
public class HelloController {
	
	private MovieRepository repository;
	public HelloController(MovieRepository repository) {
		this.repository = repository;
	}
	
	
	@RequestMapping( value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String info() {
		StringBuffer message = new StringBuffer();
		
		message.append("Seja Bem-Vindo! <br />");
		message.append("Métodos: <br />");
		message.append("/result - tras o que mais venceu. <br />");
		message.append("/getAll - trans json com todos os registros importados do csv. <br />");
		
		return message.toString();
	}
	
	@RequestMapping( value = "/results", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, HashMap<String, String>> results() {
		StringBuffer message = new StringBuffer();
		MovieDao dao = new MovieDao(repository);
		
		List<MovieEntity> lista = (List<MovieEntity>) dao.getMaior()[2];
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("producer", dao.getMaior()[1].toString());
		map.put("interval", ""+(Integer.parseInt(lista.get(1).getYear()) - Integer.parseInt(lista.get(0).getYear())));
		map.put("previous", lista.get(0).getYear());
		map.put("following", lista.get(1).getYear());
		
		HashMap<String, HashMap<String, String>> map2 = new HashMap<String, HashMap<String, String>>();
		map2.put("max", map);
		return  map2;
	}

	@RequestMapping( value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public List<MovieEntity> getAll() {
		List<MovieEntity> list = repository.findAll();
		
		return list;
	}
	
	

}
