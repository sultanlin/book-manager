package com.sultanlinjawi.library.book;

import com.sultanlinjawi.library.shelf.ShelfDto;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepo bookRepo;

    public Book getBook(int id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book does not exists"));
    }

    public Book add(Book book) {
        return bookRepo.save(book);
    }

    public void cleanup(Book book) {
        if (book.getShelves().isEmpty()) {
            bookRepo.delete(book);
        }
    }

    public List<ShelfDto> getBookShelves(int bookId, String username) {
        var book = getBook(bookId);
        return book.getShelves().stream()
                .filter(shelf -> shelf.getOwner().getUsername().equals(username))
                .map(shelf -> ShelfDto.from(shelf))
                .toList();
    }
}
