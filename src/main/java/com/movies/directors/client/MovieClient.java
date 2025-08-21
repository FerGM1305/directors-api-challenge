package com.movies.directors.client;

import com.movies.directors.exception.RemoteApiException;
import com.movies.directors.model.MoviePageResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 *
 * @author feres
 */
@Component
public class MovieClient {

    private final RestClient restClient;
    
    public MovieClient(RestClient movieRestClient) {
        this.restClient = movieRestClient;
    }
    
    public MoviePageResponse getPage(int page) {
        try {
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                    .path("/api/movies/search")
                    .queryParam("page", page)
                    .build())
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, (req, res) -> {
                        throw new RemoteApiException("Error remoto: status="
                                + res.getStatusCode(), null);
                    })
                    .body(MoviePageResponse.class);
        } catch (Exception ex) {
            throw new RemoteApiException("Fallo al consumir la API de películas", ex);
        }

    }
}
