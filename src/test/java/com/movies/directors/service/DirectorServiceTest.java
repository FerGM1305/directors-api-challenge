package com.movies.directors.service;

import com.movies.directors.client.MovieClient;
import com.movies.directors.model.Movie;
import com.movies.directors.model.MoviePageResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 *
 * @author feres
 */
@ExtendWith(MockitoExtension.class)
public class DirectorServiceTest {

    @Mock
    MovieClient movieClient;
    @InjectMocks
    DirectorService service;

    @Test
    void filtersAndSortsDirectorsStrictlyGreaterThanThreshold() {
                
        // Page 1
        var p1 = new MoviePageResponse(1, 10, 6, 2, List.of(
                new Movie("A", "2011", "Woody Allen"),
                new Movie("B", "2012", "Martin Scorsese"),
                new Movie("C", "2009", "Old Director") // year filter
        ));
        // Page 2
        var p2 = new MoviePageResponse(2, 10, 6, 2, List.of(
                new Movie("D", "2015", "Woody Allen"),
                new Movie("E", "2016", "Woody Allen"),
                new Movie("F", "2018", "Martin Scorsese")
        ));
        when(movieClient.getPage(1)).thenReturn(p1);
        when(movieClient.getPage(2)).thenReturn(p2);
        var result = service.getDirectorsAboveThreshold(2); // >2
        assertThat(result).containsExactly("Woody Allen");
    }

}
