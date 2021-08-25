package com.movie.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.movie.api.dao.MovieDao;
import com.movie.api.entity.MovieEntity;
import com.movie.api.repository.MovieRepository;

@Controller
public class MovieController {
	
	private MovieRepository repository;
	public MovieController(MovieRepository repository) {
		this.repository = repository;
	}
	
	
	@RequestMapping( value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String info() {
		StringBuffer message = new StringBuffer();
		
		message.append("Seja Bem-Vindo! <br />");
		message.append("Métodos: <br />");
		message.append("<a href='http://localhost:8080/adwards'>/adwards</a> - resultados. <br />");
		message.append("<a href='http://localhost:8080/getAll'>/getAll</a> - tras json com todos os registros importados do csv. <br />");
		
		return message.toString();
	}
	
	@RequestMapping( value = "/adwards", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> result() {
		
		MovieDao dao = new MovieDao(repository);
		
		return ResponseEntity.status(HttpStatus.OK).body(dao.getAwards());
		
	
	}

	@RequestMapping( value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public List<MovieEntity> getAll() {
		List<MovieEntity> list = repository.findAll();
		
		return list;
	}
	
	

}
