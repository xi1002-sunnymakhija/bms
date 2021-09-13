package com.example.bms.service;

import com.example.bms.domain.MovieResponse;
import com.example.bms.resource.request.DynamicFilter;
import org.elasticsearch.search.sort.SortOrder;

import java.util.List;

public interface MovieSearchService {


    List<MovieResponse> searchMovies(Integer from, Integer size, List<DynamicFilter> dynamicFilters, String sortField, SortOrder sortOrder, int threshold);
}
