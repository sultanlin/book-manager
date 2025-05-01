package com.sultanlinjawi.library.controllers;

import com.sultanlinjawi.library.dto.SearchResults;
import com.sultanlinjawi.library.services.SearchService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/api/v1/search")
    public ResponseEntity<SearchResults> search(
            @RequestParam String name, @RequestParam(defaultValue = "Book") String type) {
        var searchResult = searchService.search(name, type);
        return ResponseEntity.ok(searchResult);
    }
}
