package com.sultanlinjawi.library.services;

import com.sultanlinjawi.library.models.Book;
import com.sultanlinjawi.library.repos.BookRepo;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

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
}
