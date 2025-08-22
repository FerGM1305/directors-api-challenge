package com.movies.directors.controller;

import com.movies.directors.service.DirectorService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.constraints.Min;
import java.util.List;

import java.util.Map;
import java.util.HashMap;

/**
 * REST controller that exposes endpoints related to directors.
 * This class acts as the entry point for HTTP requests from clients.
 *
 * @author feres
 */
@RestController
@RequestMapping("/api")
@Validated
public class DirectorsController {

    private final DirectorService directorService;
    
     public DirectorsController(DirectorService directorService) {
        this.directorService = directorService;
    }
     /**
      * GET endpoint: /api/directors
      *
      * Returns a JSON object containing a list of directors who have directed more than "threshold" movies.
      * Only movies from 2010 onwards are considered.
      * @param threshold The minimum number of movies a director must have directed (must be >= 0).
      * @return A ResponseEntity containing a Map with key "directors" and the list of directors as value
      */
    @GetMapping("/directors")
    public ResponseEntity<Map<String, List<String>>>  getDirectors(@RequestParam @Min(0) int threshold) {
        //return ResponseEntity.ok(directorService.getDirectorsAboveThreshold(threshold));
    	List<String> directors = directorService.getDirectorsAboveThreshold(threshold);

        Map<String, List<String>> response = new HashMap<>();
        response.put("directors", directors);

        return ResponseEntity.ok(response);
    }
}
