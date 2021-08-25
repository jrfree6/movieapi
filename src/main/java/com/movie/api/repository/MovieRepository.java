package com.movie.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.api.entity.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
	
}
