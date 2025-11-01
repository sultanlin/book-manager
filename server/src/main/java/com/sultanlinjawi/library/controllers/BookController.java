package com.sultanlinjawi.library.controllers;

import com.sultanlinjawi.library.dto.BookDto;
import com.sultanlinjawi.library.dto.ShelfDto;
import com.sultanlinjawi.library.security.SecurityUserDetails;
import com.sultanlinjawi.library.services.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable("id") int id) {
        var bookDto = BookDto.from(bookService.getBook(id));
        return ResponseEntity.ok(bookDto);
    }

    @GetMapping("/{id}/shelves")
    public ResponseEntity<List<ShelfDto>> getBookShelves(
            @PathVariable("id") int bookId,
            @AuthenticationPrincipal SecurityUserDetails userDetails) {
        var userId = userDetails.getId();
        return ResponseEntity.ok(bookService.getBookShelves(bookId, userId));
    }
}
