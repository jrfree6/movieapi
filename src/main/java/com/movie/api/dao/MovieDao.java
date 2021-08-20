package com.movie.api.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Sort;

import com.movie.api.entity.MovieEntity;
import com.movie.api.repository.MovieRepository;

public class MovieDao  {
	
	private MovieRepository repository;
	public MovieDao(MovieRepository repository) {
		this.repository = repository;
	}
	
	public Object[] getMaior() {
		List<MovieEntity> list = repository.findAll(Sort.by(Sort.Direction.ASC, "producers"));
			
		String maiorNome = "";
		int maiorCount =0;
		int count = 0;
		String name = null, nameTmp = null ;
		List<MovieEntity> groupList = new ArrayList<MovieEntity>();
		for (MovieEntity entity : list) {
			name = entity.getProducers();
			if(entity.getWinner().equalsIgnoreCase("yes")) {
				if(name.equalsIgnoreCase(nameTmp)) {
					++count;
					if(count > maiorCount) {
						maiorCount = count;
						maiorNome = name;
						groupList.add(entity);
					}
				}else {
					count = 1;
					if(groupList.size() == 0) groupList.add(entity);
				}
				nameTmp = name;
			}

		}
		return new Object[] { maiorCount, maiorNome, groupList}; 
	}

}
