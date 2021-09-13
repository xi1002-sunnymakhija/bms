package com.example.bms.repository.impl;

import com.example.bms.repository.MovieSearchRepository;
import com.example.bms.repository.domain.Movies;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepositoryImpl implements MovieSearchRepository {
    @Override
    public List<Movies> searchMovies(Integer from, Integer size, BoolQueryBuilder boolQueryBuilder, String sortField, SortOrder sortOrder, int threshold) {
        return new ArrayList<Movies>();
    }
}
