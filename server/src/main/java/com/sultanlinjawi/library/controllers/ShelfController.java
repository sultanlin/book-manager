package com.sultanlinjawi.library.controllers;

import com.sultanlinjawi.library.dto.BookDto;
import com.sultanlinjawi.library.dto.ShelfAddRequest;
import com.sultanlinjawi.library.dto.ShelfDto;
import com.sultanlinjawi.library.security.UserDetailsImpl;
import com.sultanlinjawi.library.services.ShelfService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shelves")
public class ShelfController {
    private final ShelfService shelfService;

    @GetMapping
    public ResponseEntity<List<ShelfDto>> getShelves(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        var userId = userDetails.getId();
        return ResponseEntity.ok(shelfService.getShelves(userId));
    }

    @PostMapping
    public ResponseEntity<ShelfDto> createShelf(
            @RequestBody ShelfAddRequest shelfAddRequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        var userId = userDetails.getId();
        return ResponseEntity.ok(shelfService.createShelf(shelfAddRequest.getShelfName(), userId));
    }

    @DeleteMapping("/{shelfId}")
    public ResponseEntity<Void> deleteShelf(
            @PathVariable int shelfId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        var userId = userDetails.getId();
        shelfService.deleteShelf(shelfId, userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{shelfId}")
    public ResponseEntity<ShelfDto> updateShelf(
            @PathVariable int shelfId,
            @RequestBody ShelfAddRequest shelfAddRequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        var userId = userDetails.getId();
        return ResponseEntity.ok(
                shelfService.updateShelf(shelfId, shelfAddRequest.getShelfName(), userId));
    }

    @GetMapping("/{shelfId}/books")
    public ResponseEntity<List<BookDto>> getBooksFromShelf(
            @PathVariable int shelfId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        var userId = userDetails.getId();
        var books = shelfService.getBooksFromShelf(shelfId, userId);
        return ResponseEntity.ok(books);
    }

    @PostMapping("/{shelfId}/books")
    public ResponseEntity<List<BookDto>> addBookToShelf(
            // TODO: Set return to be list of books (all books for this shelf)
            @PathVariable int shelfId,
            @RequestBody BookDto bookDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        var userId = userDetails.getId();
        var books = shelfService.addBookToShelf(shelfId, bookDto, userId);
        return new ResponseEntity<>(books, HttpStatus.CREATED);
    }

    @DeleteMapping("/{shelfId}/books/{bookId}")
    public ResponseEntity<Void> removeBookFromShelf(
            @PathVariable int shelfId,
            @PathVariable int bookId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        var userId = userDetails.getId();
        shelfService.removeBookFromShelf(shelfId, bookId, userId);
        return ResponseEntity.noContent().build();
    }
}
