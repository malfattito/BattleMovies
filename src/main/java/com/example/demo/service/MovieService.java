package com.example.demo.service;

import com.example.demo.dto.MovieResponseDTO;
import com.example.demo.entity.Movie;
import com.example.demo.repository.MovieRepository;
import com.example.demo.utils.HttpEntityBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class MovieService {

    private static final String URI = "https://www.omdbapi.com/";
    private static final String API_KEY = "5f4c2fa0";
    private static final String STARTER_ID = "tt";
    private static final String TYPE_MOVIE = "movie";
    private static final String NA_VALUE = "N/A";


    @Autowired
    private MovieRepository movieRepository;

    private RestTemplate restTemplate;
    private HttpEntityBuilder httpEntityBuilder;
    private ObjectMapper objectMapper;

    public MovieService() {
        restTemplate = new RestTemplate();
        httpEntityBuilder = new HttpEntityBuilder();
        objectMapper = new ObjectMapper();
    }

    public void webscraping() {
        Integer indice = 1;
        for(Integer iterator = 1; iterator <= 100; ) {
            StringBuilder imdbID = new StringBuilder();
            imdbID.append(STARTER_ID);
            imdbID.append(String.format("%07d", indice));

            String urlTemplate = UriComponentsBuilder.fromHttpUrl(URI)
                    .queryParam("apiKey", "{apiKey}")
                    .queryParam("i", "{imdbid}")
                    .queryParam("type", "{type}")
                    .encode()
                    .toUriString();

            Map<String, String> params = new HashMap<>();
            params.put("apiKey", API_KEY);
            params.put("imdbid", imdbID.toString());
            params.put("type", TYPE_MOVIE);

            ResponseEntity<String> response = restTemplate.exchange(urlTemplate, HttpMethod.GET,
                    httpEntityBuilder.comHeadersDefault().build(), String.class, params);

            try {
                MovieResponseDTO movieResponseDTO = objectMapper.readValue(response.getBody(), MovieResponseDTO.class);
                if (movieValid(movieResponseDTO)) {
                    movieRepository.save(movieDTOToMovie(movieResponseDTO));
                    indice++;
                    iterator++;
                }
                indice++;
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Movie movieDTOToMovie(MovieResponseDTO movieResponseDTO){
        return new Movie(movieResponseDTO.getTitle(), movieResponseDTO.getUrlImage(), Double.parseDouble(movieResponseDTO.getRating()));
    }

    private boolean movieValid(MovieResponseDTO responseDTO) {
        boolean valido = true;
        if(responseDTO.getTitle().compareTo(NA_VALUE) == 0
                || responseDTO.getRating().compareTo(NA_VALUE) == 0
                || responseDTO.getUrlImage().compareTo(NA_VALUE) == 0) {
            valido = false;
        }
        return valido;
    }

}
