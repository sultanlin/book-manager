package com.sultanlinjawi.library.controllers;

import com.sultanlinjawi.library.dto.BookDto;
import com.sultanlinjawi.library.services.SearchService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/api/v1/search")
    public ResponseEntity<List<BookDto>> search(
            @RequestParam String name, @RequestParam(defaultValue = "Book") String type) {
        return ResponseEntity.ok(searchService.search(name, type));
    }
}
