package com.movies.directors.service;

import com.movies.directors.client.MovieClient;
import com.movies.directors.model.Movie;
import com.movies.directors.model.MoviePageResponse;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 *
 * @author feres
 */
@Service
public class DirectorService {

    private final MovieClient movieClient;
    
    public DirectorService(MovieClient movieClient) {
        this.movieClient = movieClient;
    }
    
    public List<String> getDirectorsAboveThreshold(int threshold) {
        // Obtener la primera página
        MoviePageResponse firstPage = movieClient.getPage(1);

        // Contador de películas por director
        Map<String, Integer> directorCounts = new HashMap<>();
        accumulate(firstPage, directorCounts);

        // Procesar las demás páginas
        int totalPages = Math.max(1, firstPage.getTotalPages());
        for (int page = 2; page <= totalPages; page++) {
            MoviePageResponse nextPage = movieClient.getPage(page);
            accumulate(nextPage, directorCounts);
        }

        // Filtrar y construir resultado
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : directorCounts.entrySet()) {
            String director = entry.getKey();
            int count = entry.getValue();

            if (director != null && !director.isBlank() && count > threshold) {
                result.add(director.trim());
            }
        }

        // Ordenar de manera insensible a mayúsculas
        result.sort(String::compareToIgnoreCase);

        return result;
    }
    

    private void accumulate(MoviePageResponse page, Map<String, Integer> counts) {
        if (page == null || page.getData() == null) {
            return;
        }
        for (Movie m : page.getData()) {
            // La API dice > 2010; por si acaso filtramos defensivamente
            int year = safeParseYear(m.getYear());
            if (year >= 2010) {
                String director = m.getDirector();
                if (director != null && !director.isBlank()) {
                    counts.merge(director.trim(), 1, Integer::sum);
                }
            }
        }
    }

    private int safeParseYear(String s) {
        if (s == null) {
            return -1;
        }
        try {
            return Integer.parseInt(s.replaceAll("\\D", ""));
        } catch (Exception e) {
            return -1;
        }
    }

}
