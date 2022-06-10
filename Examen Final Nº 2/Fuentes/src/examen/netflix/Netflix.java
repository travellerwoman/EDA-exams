package examen.netflix;

import java.util.*;

public class Netflix {

	Map<String, HashSet<Movie>> titleMap;
	Map<Integer, HashSet<Movie>> yearMap;
	Map<String, HashSet<Movie>> typeMovie;
	HashSet<Movie> movieHashSet;

	public Netflix() {
		titleMap = new HashMap<>();
		yearMap = new HashMap<>();
		typeMovie = new HashMap<>();
		movieHashSet = new HashSet<>();
	}

	public void addContent(String title, int year, ArrayList<String> types) {
		if (title != null && year > 0 && types != null) {
			Movie movie = new Movie(title, year, types);
			addTitle(title, movie);
			addYear(year, movie);
			addType(types, movie);

			movieHashSet.add(movie);
		}
	}

	private void addType(ArrayList<String> types, Movie movie) {
		for (String type : types) {
			HashSet<Movie> movies = typeMovie.get(type);
			if (movies == null) {
				movies = new HashSet<>();
			}
			movies.add(movie);
			typeMovie.put(type, movies);
		}
	}

	private void addYear(int year, Movie movie) {
		HashSet<Movie> movies = yearMap.get(year);
		if (movies == null){
			movies = new HashSet<>();
		}
		movies.add(movie);
		yearMap.put(year, movies);
	}

	private void addTitle(String title, Movie movie) {
		HashSet<Movie> movies = titleMap.get(title);
		if (movies == null){
			movies = new HashSet<>();
		}
		movies.add(movie);
		titleMap.put(title, movies);
	}

	public Iterable<Movie> findTitle(String title){
		return titleMap.get(title);
	}
	
	public Iterable<Movie> findYear(int year){
		return yearMap.get(year);
	}
	
	
	public Iterable<Movie> findType(String type) {
		return typeMovie.get(type);
	}
	
	public Iterable<Movie> findType(Set<String> types){
		HashSet<Movie> movies = null;
		for (String type : types) {
			if (movies == null){
				movies = typeMovie.get(type);
			} else {
				movies.retainAll(typeMovie.get(type));
			}
		}
		return movies;
	}
	
	
	
}
