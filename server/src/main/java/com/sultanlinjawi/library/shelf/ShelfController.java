package com.sultanlinjawi.library.shelf;

import com.sultanlinjawi.library.book.BookDto;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shelves")
public class ShelfController {
    private final ShelfService shelfService;

    @GetMapping
    public ResponseEntity<List<ShelfDto>> getShelves(Principal principal) {
        return ResponseEntity.ok(shelfService.getShelves(principal.getName()));
    }

    @PostMapping
    public ResponseEntity<ShelfDto> createShelf(
            @RequestBody ShelfAddRequest shelfAddRequest, Principal principal) {
        return ResponseEntity.ok(
                shelfService.createShelf(shelfAddRequest.getShelfName(), principal.getName()));
    }

    @DeleteMapping("/{shelfId}")
    public ResponseEntity<Void> deleteShelf(@PathVariable int shelfId, Principal principal) {
        shelfService.deleteShelf(shelfId, principal.getName());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{shelfId}")
    public ResponseEntity<ShelfDto> updateShelf(
            @PathVariable int shelfId,
            @RequestBody ShelfAddRequest shelfAddRequest,
            Principal principal) {
        return ResponseEntity.ok(
                shelfService.updateShelf(
                        shelfId, shelfAddRequest.getShelfName(), principal.getName()));
    }

    @GetMapping("/{shelfId}/books")
    public ResponseEntity<List<BookDto>> getBooksFromShelf(
            @PathVariable int shelfId, Principal principal) {
        return ResponseEntity.ok(shelfService.getBooksFromShelf(shelfId, principal.getName()));
    }

    @PostMapping("/{shelfId}/books")
    public ResponseEntity<List<BookDto>> addBookToShelf(
            @PathVariable int shelfId, @RequestBody BookDto bookDto, Principal principal) {
        return new ResponseEntity<>(
                shelfService.addBookToShelf(shelfId, bookDto, principal.getName()),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{shelfId}/books/{bookId}")
    public ResponseEntity<List<BookDto>> removeBookFromShelf(
            @PathVariable int shelfId, @PathVariable int bookId, Principal principal) {
        return ResponseEntity.ok(
                shelfService.removeBookFromShelf(shelfId, bookId, principal.getName()));
    }
}
