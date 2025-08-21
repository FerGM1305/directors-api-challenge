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
    
    @GetMapping("/directors")
    public ResponseEntity<List<String>> getDirectors(@RequestParam @Min(0) int threshold) {
        return ResponseEntity.ok(directorService.getDirectorsAboveThreshold(threshold));
    }
}
