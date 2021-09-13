package com.example.bms.resource;

import com.example.bms.domain.MovieResponse;
import com.example.bms.resource.request.DynamicFilter;
import com.example.bms.service.MovieSearchService;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie/search")
public class MovieSearchResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieSearchResource.class);

    @Autowired
    MovieSearchService saerchService;

    @PostMapping("/search")
    public List<MovieResponse> searchCases(@RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
                                           @RequestParam(value = "size", required = false, defaultValue = "10000") Integer size,
                                           @RequestParam(value = "sort-field", required = false) String sortField,
                                           @RequestParam(value = "sort-order", required = false) SortOrder sortOrder,
                                           @RequestParam(value = "threshold", defaultValue = "10000") int threshold,
                                           @RequestBody List<DynamicFilter> dynamicFilters) {
        List<MovieResponse> searchResponse = saerchService.searchMovies(from, size, dynamicFilters, sortField, sortOrder, threshold);
        LOGGER.info("Successfully processed request to search cases with hits : {} in time(ms) : {}",
                searchResponse.size());
        return searchResponse;
    }
}
