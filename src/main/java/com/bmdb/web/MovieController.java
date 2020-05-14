package com.bmdb.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.JsonResponse;
import com.bmdb.business.Movie;
import com.bmdb.db.MovieRepository;

@CrossOrigin
@RestController
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	private MovieRepository movieRepo;

	@GetMapping("/")
	public JsonResponse list() {
		JsonResponse jr = null;
		List<Movie> movies = movieRepo.findAll();
		if (movies.size() > 0) {
			jr = JsonResponse.getInstance(movies);
			
		} else {
			jr = JsonResponse.getErrorInstance("No movies found.");
		}
		return jr;
	}

	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		// expected responses?
		// 1 - a single movie
		// 2 - bad id - no movie found
		// 3 - Exception?? - hold off for now, implement exception handling as needed
		JsonResponse jr = null;
		Optional<Movie> movie = movieRepo.findById(id);
		if (movie.isPresent()) {
			jr = JsonResponse.getInstance(movie.get());
		} else {
			jr = JsonResponse.getErrorInstance("No movie found for id: " + id);
		}
		return jr;
	}
	
	// 'create' method
	@PostMapping("/")
	public JsonResponse createMovie(@RequestBody Movie m) {
		JsonResponse jr = null;
		try {
			m = movieRepo.save(m);
			jr = JsonResponse.getInstance(m);
		} 
		catch (DataIntegrityViolationException dive) {
			jr = JsonResponse.getErrorInstance(dive.getRootCause().getMessage());
			dive.printStackTrace();
		}
		catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error creating movie: "+e.getMessage());
			e.printStackTrace();
		}
		
		return jr;
	}
	
	// 'update' method
	@PutMapping("/")
	public JsonResponse updateMovie(@RequestBody Movie m) {
		JsonResponse jr = null;
		try {
			m = movieRepo.save(m);
			jr = JsonResponse.getInstance(m);
		} catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error updating movie: "+e.getMessage());
			e.printStackTrace();
		}
		
		return jr;
	}
	
	// 'delete' method
	@DeleteMapping("/{id}")
	public JsonResponse deleteMovie(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			movieRepo.deleteById(id);
			jr = JsonResponse.getInstance("Movie id: "+id+" deleted successfully.");
		} catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error deleting movie: "+e.getMessage());
			e.printStackTrace();
		}
		
		return jr;
	}

}
