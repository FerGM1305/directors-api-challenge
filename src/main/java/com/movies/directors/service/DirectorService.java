package com.movies.directors.service;

import com.movies.directors.client.MovieClient;
import com.movies.directors.model.Movie;
import com.movies.directors.model.MoviePageResponse;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 *Service class that handles the logic for analyzing directors
 * based on the movie data retrieved from the external API. 
 * @author feres
 */
@Service
public class DirectorService {

    private final MovieClient movieClient;
    
    public DirectorService(MovieClient movieClient) {
        this.movieClient = movieClient;
    }
    /**
     * Returns a list of directors who have directed more than
     * the given threshold number of movies (since 2010).
     * @param threshold The minimum number of movies a director must have.
     * @return A sorted list of directors above the threshold.
     */
    public List<String> getDirectorsAboveThreshold(int threshold) {
        // Get first page
        MoviePageResponse firstPage = movieClient.getPage(1);

        // Movie Directors counter
        Map<String, Integer> directorCounts = new HashMap<>();
        accumulate(firstPage, directorCounts);

        int totalPages = Math.max(1, firstPage.getTotalPages());
        for (int page = 2; page <= totalPages; page++) {
            MoviePageResponse nextPage = movieClient.getPage(page);
            accumulate(nextPage, directorCounts);
        }

        // Filter directors who meet the threshold and clean up names
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : directorCounts.entrySet()) {
            String director = entry.getKey();
            int count = entry.getValue();

            if (director != null && !director.isBlank() && count > threshold) {
                result.add(director.trim());
            }
        }

        // Sort alphabetically, ignoring case
        result.sort(String::compareToIgnoreCase);

        return result;
    }
    
    /**
     * Helper method to process a page of movies and update the counts.
     * Only movies from 2010 onwards are considered.
    */
    private void accumulate(MoviePageResponse page, Map<String, Integer> counts) {
        if (page == null || page.getData() == null) {
            return;
        }
        for (Movie m : page.getData()) {
        	// Defensive check: make sure year is 2010 or later
            int year = safeParseYear(m.getYear());
            if (year >= 2010) {
                String director = m.getDirector();
                if (director != null && !director.isBlank()) {
                    counts.merge(director.trim(), 1, Integer::sum);
                }
            }
        }
    }
    
    /**
     * Safely parses a year from a string.
     * Removes any non-digit characters before parsing.
     * Returns -1 if parsing fails.
     */
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
