package com.example.bms.repository;

import com.example.bms.repository.domain.Movies;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.util.List;

public interface MovieSearchRepository {
    List<Movies> searchMovies(Integer from, Integer size, BoolQueryBuilder boolQueryBuilder, String sortField, SortOrder sortOrder, int threshold);
}
