package com.movie.api.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;

import com.movie.api.entity.MovieEntity;
import com.movie.api.helper.Helper;
import com.movie.api.repository.MovieRepository;
import com.movie.api.result.Max;
import com.movie.api.result.Min;
import com.movie.api.result.RootElement;

public class MovieDao  {
	
	private MovieRepository repository;
	public MovieDao(MovieRepository repository) {
		this.repository = repository;
	}
	
	public Object getAwards() {
		List<MovieEntity> list = repository.findAll(Sort.by(Sort.Direction.ASC, "producers"));
		
		List<MovieEntity> listOrderByProducers = new ArrayList<MovieEntity>();
		MovieEntity movieTmp = null;
		for (MovieEntity movieEntity : list) {
			if(!movieEntity.getWinner().equalsIgnoreCase("yes")) continue;
			String[] producersTmp = (movieEntity.getProducers().replace("and", ",")).split(",");
			movieTmp = null;
			for (String producersAux : producersTmp) {
				movieTmp = new MovieEntity();
				movieTmp.setProducers(producersAux.trim());
				movieTmp.setStudios(movieEntity.getStudios());
				movieTmp.setTitle(movieEntity.getTitle());
				movieTmp.setYear(movieEntity.getYear());
				movieTmp.setWinner(movieEntity.getWinner());
				listOrderByProducers.add(movieTmp);
			}
			
		}
		
		Collections.sort(listOrderByProducers, Comparator.comparing(MovieEntity::getProducers).thenComparing(MovieEntity::getYear));

		List<Max> listMax = new ArrayList<Max>();
		List<Min> listMin = new ArrayList<Min>();

		int min = Short.MAX_VALUE;
		int max = Short.MIN_VALUE;
		
		movieTmp = null;
		for (MovieEntity movieTo : listOrderByProducers) {
			if(movieTmp != null) {
				if(movieTmp.getProducers().equalsIgnoreCase(movieTo.getProducers())) {
					int interval = Helper.stringToInteger(movieTo.getYear() )- Helper.stringToInteger(movieTmp.getYear());
					if(interval <= min && interval==1) {
						listMin.add(new Min(movieTo.getProducers(), interval, Helper.stringToInteger(movieTmp.getYear()), Helper.stringToInteger(movieTo.getYear())));
					}
					if(interval >= max) {
						listMax.add(new Max(movieTo.getProducers(), interval, Helper.stringToInteger(movieTmp.getYear()), Helper.stringToInteger(movieTo.getYear())));
					}
				}
			}
			movieTmp = movieTo;
		}
	
		Collections.sort(listMax, Comparator.comparing(Max::getInterval).reversed());
		Collections.sort(listMin, Comparator.comparing(Min::getInterval));
		
		RootElement rootElement = new RootElement();
		
		List<Max> resultMax = listMax.stream().limit(2).collect(Collectors.toList());
		List<Min> resultMin = listMin.stream().limit(2).collect(Collectors.toList());
		rootElement.setMax(resultMax);
		rootElement.setMin(resultMin);

		return rootElement;

	}

}
