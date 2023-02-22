package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {
    HashMap<String, Movie> movieHashMap = new HashMap<>();
    HashMap<String, Director> directorHashMap = new HashMap<>();
    HashMap<String, List<Movie>> directorMovieMap = new HashMap<>();

    public void addMovie(Movie movie){
        movieHashMap.put(movie.getName(), movie);
    }

    public void addDirector(Director director){
        directorHashMap.put(director.getName(), director);
    }

    public void addMovieDirectorPair(Director director, Movie movie){
        List<Movie> curr = directorMovieMap.getOrDefault(director.getName(), new ArrayList<>());
        curr.add(movie);
        directorMovieMap.put(director.getName(), curr);
    }

    public Movie getMovieByName(String movieName){
        return movieHashMap.get(movieName);
    }

    public Director getDirectorByName(String directorName){
        return directorHashMap.get(directorName);
    }

    public List<Movie> getMoviesByDirectorName(String directorName){
        return directorMovieMap.get(directorName);
    }

    public List<Movie> findAllMovies(){
        List<Movie> allMovies = new ArrayList<>();
        for(String movieName: movieHashMap.keySet()){
            allMovies.add(movieHashMap.get(movieName));
        }

        return allMovies;
    }

    public void deleteDirectorByName(String directorName){
        List<Movie> movies = getMoviesByDirectorName(directorName);
        for(Movie movie: movies){
            movieHashMap.remove(movie.getName());
        }
        directorHashMap.remove(directorName);
    }

    public void deleteAllDirectors(){
        for(String directorName: directorMovieMap.keySet()){
            deleteDirectorByName(directorName);
        }
    }

}
