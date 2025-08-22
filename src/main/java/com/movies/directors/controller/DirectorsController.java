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
      * Returns a list of directors who have directed more than "threshold" movies.
      * Only movies from 2010 onwards are considered.
      * @param threshold The minimum number of movies a director must have directed (must be >= 0).
      * @return A ResponseEntity containing the list of directors.
      */
    @GetMapping("/directors")
    public ResponseEntity<List<String>> getDirectors(@RequestParam @Min(0) int threshold) {
        return ResponseEntity.ok(directorService.getDirectorsAboveThreshold(threshold));
    }
}
