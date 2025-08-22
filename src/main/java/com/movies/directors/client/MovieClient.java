package com.movies.directors.client;

import com.movies.directors.exception.RemoteApiException;
import com.movies.directors.model.MoviePageResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * This class acts as a client to communicate with the external Movie API.
 * It uses Spring's RestClient to send HTTP requests and parse responses.
 * @author feres
 */
@Component
public class MovieClient {

    private final RestClient restClient;
    
    public MovieClient(RestClient movieRestClient) {
        this.restClient = movieRestClient;
    }
    
    // Fetches a page of movies from the external API.
    public MoviePageResponse getPage(int page) {
    	try {
            return restClient.get()
                    // Build the request URL dynamically
                    .uri(uriBuilder -> uriBuilder
                        .path("/api/movies/search")   // endpoint of the remote API
                        .queryParam("page", page)    // attach the page parameter
                        .build())
                    .retrieve()  // Execute the request
                    // If the API responds with an error status, throw a custom exception
                    .onStatus(HttpStatusCode::isError, (req, res) -> {
                        throw new RemoteApiException("Remote error: status="
                                + res.getStatusCode(), null);
                    })
                    // Deserialize the response body into a MoviePageResponse object
                    .body(MoviePageResponse.class);
        } catch (Exception ex) {
            // Wrap any unexpected errors in a custom exception
            throw new RemoteApiException("Failed to consume the Movie API", ex);
        }

    }
}
