package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies") // base url
public class MovieController {
    @Autowired
    MovieService movieService;
    // object can be created w.o using new keyword
    // Dependency Injection (DI)

    // All APIs go as following:

    // 1
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody() Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity<>("Movie added successfully", HttpStatus.CREATED);
    }

    // 2
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody() Director director){
        movieService.addDirector(director);
        return new ResponseEntity<>("Director added successfully", HttpStatus.CREATED);
    }

    // 3
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movieName, @RequestParam("director") String directorName){
        movieService.addMovieDirectorPair(directorName, movieName);
        return new ResponseEntity<>("Movie-Director Pair added successfully", HttpStatus.CREATED);
    }

    // 4
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String movieName){
        Movie currMovie = movieService.getMovieByName(movieName);
        return new ResponseEntity<>(currMovie, HttpStatus.CREATED);
    }

    // 5
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String directorName){
        Director currDirector = movieService.getDirectorByName(directorName);
        return new ResponseEntity<>(currDirector, HttpStatus.CREATED);
    }

    // 6
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<Movie>> getMoviesByDirectorName(@PathVariable("director") String directorName){
        List<Movie> allMovies = movieService.getMoviesByDirectorName(directorName);
        return new ResponseEntity<>(allMovies, HttpStatus.CREATED);
    }

    // 7
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<Movie>> findAllMovies(){
        List<Movie> allMovies = movieService.findAllMovies();
        return new ResponseEntity<>(allMovies, HttpStatus.CREATED);
    }

    // 8
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("director") String directorName){
        movieService.deleteDirectorByName(directorName);
        return new ResponseEntity<>("Director has been deleted successfully", HttpStatus.CREATED);
    }

    // 9
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("All Directors have been deleted successfully", HttpStatus.CREATED);
    }

}
