package com.sultanlinjawi.library.controllers;

import com.sultanlinjawi.library.models.Book;
import com.sultanlinjawi.library.services.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }
}
