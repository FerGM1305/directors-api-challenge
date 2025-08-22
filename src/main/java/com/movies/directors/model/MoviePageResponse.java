package com.movies.directors.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 *Represents a paginated response from the Movie API.
 * This class maps the JSON structure returned by the API
 * into a Java object we can easily work with.
 * @author feres
 */
public class MoviePageResponse {

	 // The current page number
    private int page;

    // Maps the JSON property "per_page" to this field
    @JsonProperty("per_page")
    private int perPage;

    // The total number of records available
    private int total;

    // Maps the JSON property "total_pages" to this field
    @JsonProperty("total_pages")
    private int totalPages;

    // The actual list of movies for this page
    private List<Movie> data;
    
    public MoviePageResponse() {
    }
    
    
    // Full constructor to create a response manually
    public MoviePageResponse(int page, int perPage, int total, int totalPages, List<Movie> data) {
        this.page = page;
        this.perPage = perPage;
        this.total = total;
        this.totalPages = totalPages;
        this.data = data;
    }
    
    
    // Getters and setters
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Movie> getData() {
        return data;
    }

    public void setData(List<Movie> data) {
        this.data = data;
    }

}
