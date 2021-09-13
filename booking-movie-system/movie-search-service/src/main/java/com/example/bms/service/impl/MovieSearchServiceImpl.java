package com.example.bms.service.impl;

import com.example.bms.domain.MovieResponse;
import com.example.bms.repository.MovieSearchRepository;
import com.example.bms.repository.domain.Movies;
import com.example.bms.resource.request.DynamicFilter;
import com.example.bms.service.MovieSearchService;
import com.example.bms.service.QueryBuilderService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieSearchServiceImpl implements MovieSearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieSearchServiceImpl.class);

    @Autowired
    QueryBuilderService queryBuilderService;

    @Autowired
    MovieSearchRepository searchRepository;

    @Override
    public List<MovieResponse> searchMovies(Integer from, Integer size, List<DynamicFilter> dynamicFilters, String sortField, SortOrder sortOrder, int threshold) {
        LOGGER.info("Processing request to fetch leads from repository");
        BoolQueryBuilder boolQueryBuilder = queryBuilderService.buildBooleanQueryForSearchParameters(dynamicFilters);
        List<Movies> caseEntities = searchRepository.searchMovies(from, size, boolQueryBuilder, sortField, sortOrder, threshold);
        List<MovieResponse> cases = buildMovieResponse(caseEntities);
        LOGGER.info("Successfully processed request to fetch leads from repository");
        return cases;
    }

    private List<MovieResponse> buildMovieResponse(List<Movies> caseEntities) {
        List<MovieResponse> movieResponses = new ArrayList<>();
        caseEntities.stream().forEach(movie -> {
            movieResponses.add(MovieResponse.builder().withMovieID(movie.getMovieID()).withMovieName(movie.getMovieName()).withActorName(movie.getActorName()).build());
        });
        return movieResponses;
    }
}
