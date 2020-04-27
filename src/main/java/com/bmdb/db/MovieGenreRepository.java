package com.bmdb.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmdb.business.MovieGenre;

public interface MovieGenreRepository extends JpaRepository<MovieGenre, Integer> {
	// in sql:
	// select * from MovieGenre 
	// where movieID = ?
	List<MovieGenre> findAllByMovieId(int movieID);
}
