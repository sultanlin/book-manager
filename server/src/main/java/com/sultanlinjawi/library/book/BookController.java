package com.sultanlinjawi.library.book;

import com.sultanlinjawi.library.shelf.ShelfDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/books")
class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable("id") int id) {
        var bookDto = BookDto.from(bookService.getBook(id));
        return ResponseEntity.ok(bookDto);
    }

    @GetMapping("/{id}/shelves")
    public ResponseEntity<List<ShelfDto>> getBookShelves(
            @PathVariable("id") int bookId, Principal principal) {
        return ResponseEntity.ok(bookService.getBookShelves(bookId, principal.getName()));
    }
}
