
package com.movies.directors.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *Represents a Movie entity coming from the external API.
 * @author feres
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
	// Map the JSON property "Title" to this field
    @JsonProperty("Title")
    private String title;

    // Map the JSON property "Year" to this field
    @JsonProperty("Year")
    private String year;

    // Map the JSON property "Director" to this field
    @JsonProperty("Director")
    private String director;

    /**
     * Default constructor required by Jackson for deserialization.
     */
    public Movie() {
    }

    public Movie(String title, String year, String director) {
        this.title = title;
        this.year = year;
        this.director = director;
    }
    
    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
