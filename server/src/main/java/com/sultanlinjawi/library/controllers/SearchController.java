package com.sultanlinjawi.library.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.sultanlinjawi.library.services.SearchService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/api/v1/search")
    public JsonNode search(
            @RequestParam String name, @RequestParam(defaultValue = "Book") String type) {
        return searchService.search(name, type);
    }
}
