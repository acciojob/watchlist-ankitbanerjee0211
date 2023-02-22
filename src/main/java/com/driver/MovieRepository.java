package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {
    HashMap<String, Movie> movieHashMap = new HashMap<>();
    HashMap<String, Director> directorHashMap = new HashMap<>();
    HashMap<String, List<String>> directorMovieMap = new HashMap<>();

    public void addMovie(Movie movie){
        movieHashMap.put(movie.getName(), movie);
    }

    public void addDirector(Director director){
        directorHashMap.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String director, String movie){
        List<String> curr = directorMovieMap.getOrDefault(director, new ArrayList<>());
        curr.add(movieHashMap.get(movie).getName());
        directorMovieMap.put(director, curr);
    }

    public Movie getMovieByName(String movieName){
        return movieHashMap.get(movieName);
    }

    public Director getDirectorByName(String directorName){
        return directorHashMap.get(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName){
        return directorMovieMap.get(directorName);
    }

    public List<String> findAllMovies(){
        List<String> allMovies = new ArrayList<>();
        allMovies.addAll(movieHashMap.keySet());
        return allMovies;
    }

    public void deleteDirectorByName(String directorName){
        List<String> movies = getMoviesByDirectorName(directorName);
        for(String movie: movies){
            movieHashMap.remove(movie);
        }
        directorHashMap.remove(directorName);
    }

    public void deleteAllDirectors(){
        for(String directorName: directorMovieMap.keySet()){
            deleteDirectorByName(directorName);
        }
    }

}
