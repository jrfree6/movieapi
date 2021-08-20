package com.movie.api.service;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.movie.api.entity.MovieEntity;
import com.movie.api.repository.MovieRepository;

@Service
public class MovieService implements ApplicationRunner {
	
	@Value("classpath:data/movielist.csv")
	private Resource movieListFile;
	
	private final MovieRepository repository;
	
	private static final Logger LOGGER = Logger.getLogger(MovieEntity.class.getName());
	
	public MovieService(MovieRepository repository) {
		this.repository = repository;
	}
	

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		LOGGER.log(Level.INFO, "Inicia carregamento do arquivo csv....");
		CsvSchema schema =  CsvSchema.builder().
												addColumn("year").
												addColumn("title").
												addColumn("studios").
												addColumn("producers").
												addColumn("winner").
												setUseHeader(true)
												.build();
		schema = schema.withColumnSeparator(';');
		
		CsvMapper mapper = new CsvMapper();
		MappingIterator<MovieEntity> readValues = mapper.readerFor(MovieEntity.class).
														with(schema).
														readValues(movieListFile.getInputStream());
		
		List<MovieEntity> list = new ArrayList<MovieEntity>();
		try {
			list = readValues.readAll();	
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Estrutura do arquivo CSV nao atende ao layout esperado.");
			return;
		}
		
		LOGGER.log(Level.INFO, "Persist");
		for (MovieEntity entity : list) {
//			System.out.println("Year:" + entity.getYear()+
//								" - Title:"+entity .getTitle() +
//								" - Winner:"+entity.getWinner() );
			repository.save(entity);
		}
		LOGGER.log(Level.INFO, "Complete");
		
	}

}
