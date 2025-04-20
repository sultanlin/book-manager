package com.sultanlinjawi.library.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.sultanlinjawi.library.service.SearchService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/api/v1/search")
    public JsonNode search(
            @RequestParam String name, @RequestParam(defaultValue = "Book") String type) {
        return searchService.search(name, type);
    }
}
