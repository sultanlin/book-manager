package com.sultanlinjawi.library.services;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.sultanlinjawi.library.book.Book;
import com.sultanlinjawi.library.book.BookRepo;
import com.sultanlinjawi.library.book.BookService;
import com.sultanlinjawi.library.shelf.Shelf;
import com.sultanlinjawi.library.user.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {
    @Mock private BookRepo bookRepo;

    @InjectMocks private BookService service;

    @Test
    @DisplayName("Cleanup book, delete book if it is not related to any shelf")
    public void CleanUpBookIfItHasNoShelfRelation() {
        var book = Book.builder().id(10).build();
        service.cleanup(book);
        verify(bookRepo).delete(book);
    }

    @Test
    @DisplayName("Cleanup book, do nothing if shelf is not empty")
    public void NoCleanUpIfItHasShelfRelation() {
        var user = User.builder().id(1).username("tester 1").password("pass").build();
        var shelf = Shelf.builder().name("shelf1").owner(user).build();
        var book = Book.builder().id(10).build();
        book.getShelves().add(shelf);
        service.cleanup(book);
        verify(bookRepo, never()).delete(book);
    }
}
