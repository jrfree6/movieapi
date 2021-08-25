package com.movie.api.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.movie.api.controller.MovieController;

@SpringBootTest
class MovieControllerTest {
	
	@Autowired
	private MovieController movieController;

	@Test
	void test() throws Exception {
		assertThat(movieController).isNotNull();
	}

}
