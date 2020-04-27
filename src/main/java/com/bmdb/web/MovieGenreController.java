package com.bmdb.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.JsonResponse;
import com.bmdb.business.MovieGenre;
import com.bmdb.db.MovieGenreRepository;

@RestController
@RequestMapping("/movie-genres")
public class MovieGenreController {
	@Autowired
	private MovieGenreRepository movieGenreRepo;
	
	@GetMapping("/")
	public JsonResponse list() {
		JsonResponse jr = null;
		List<MovieGenre> movieGenres = movieGenreRepo.findAll();
		if (movieGenres.size() > 0) {
			jr = JsonResponse.getInstance(movieGenres);			
		}
		else {
			jr = JsonResponse.getErrorInstance("No movieGenres found.");
		}
		return jr;
	}
	// Disclaimer - This method may not follow strict API Style Guide rules
	@GetMapping("/by-movie-id")
	public JsonResponse listByMovieId(@RequestParam int movieId) {
		JsonResponse jr = null;
		List<MovieGenre> movieGenres = movieGenreRepo.findAllByMovieId(movieId);
		if (movieGenres.size() > 0) {
			jr = JsonResponse.getInstance(movieGenres);			
		}
		else {
			jr = JsonResponse.getErrorInstance("No movieGenres found for movie id: "+movieId+". ");
		}
		return jr;
	}

	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {

		JsonResponse jr = null;
		Optional<MovieGenre> movieGenre = movieGenreRepo.findById(id);
		if (movieGenre.isPresent()) {
			jr = JsonResponse.getInstance(movieGenre.get());
		}
		else {
			jr = JsonResponse.getErrorInstance("No movieGenre found for id: "+id);
		}
		return jr;
	}
	
	// 'create' method
	@PostMapping("/")
	public JsonResponse createMovieGenre(@RequestBody MovieGenre mg) {
		JsonResponse jr = null;
		
		try {
			mg = movieGenreRepo.save(mg);
			jr = JsonResponse.getInstance(mg);
		} 
		catch (DataIntegrityViolationException dive) {
			jr = JsonResponse.getErrorInstance(dive.getRootCause().getMessage());
			dive.printStackTrace();
		}
		catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error creating movieGenre: "+e.getMessage());
			e.printStackTrace();
		}
		
		return jr;
	}
	
	@PutMapping("/")
	public JsonResponse updateMovieGenre(@RequestBody MovieGenre mg) {
		JsonResponse jr = null;
		
		try {
			mg = movieGenreRepo.save(mg);
			jr = JsonResponse.getInstance(mg);
		} catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error updating movieGenre: "+e.getMessage());
			e.printStackTrace();
		}
		
		return jr;
	}
	
	@DeleteMapping("/{id}")
	public JsonResponse deleteMovieGenre(@PathVariable int id) {
		JsonResponse jr = null;
		
		try {
			movieGenreRepo.deleteById(id);
			jr = JsonResponse.getInstance("MovieGenre id: "+id+" deleted successfully.");
		} catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error deleting movieGenre: "+e.getMessage());
			e.printStackTrace();
		}
		
		return jr;
	}
	
	
	
	
	
	

}
