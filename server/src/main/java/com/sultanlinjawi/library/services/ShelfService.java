package com.sultanlinjawi.library.services;

import com.sultanlinjawi.library.dto.BookDto;
import com.sultanlinjawi.library.dto.ShelfDto;
import com.sultanlinjawi.library.models.Book;
import com.sultanlinjawi.library.models.Shelf;
import com.sultanlinjawi.library.repos.ShelfRepo;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShelfService {
    private final ShelfRepo shelfRepo;
    private final UserService userService;
    private final BookService bookService;

    @Transactional
    public List<ShelfDto> getShelves(int userId) {
        var user = userService.getUserById(userId);
        var shelves = user.getShelves();
        return shelves.stream().map((shelf) -> ShelfDto.from(shelf)).toList();
    }

    @Transactional
    public ShelfDto createShelf(String shelfName, int userId) {
        var user = userService.getUserById(userId);
        var shelf = Shelf.builder().name(shelfName).owner(user).build();
        var existingShelf =
                user.getShelves().stream().filter(s -> s.getName().equals(shelfName)).findFirst();
        if (existingShelf.isPresent()) {
            throw new IllegalArgumentException("Shelf with this name already exists for this user");
        }
        return ShelfDto.from(shelfRepo.save(shelf));
    }

    @Transactional
    public ShelfDto updateShelf(int shelfId, String shelfName, int userId) {
        var shelf = getShelf(shelfId, userId);
        shelf.setName(shelfName);
        return ShelfDto.from(shelfRepo.save(shelf));
    }

    @Transactional
    public void deleteShelf(int shelfId, int userId) {
        var shelf = getShelf(shelfId, userId);
        shelf.getBooks().stream()
                .forEach(
                        (book) -> {
                            book.getShelves().remove(shelf);
                            bookService.cleanup(book);
                        });
        shelfRepo.delete(shelf);
    }

    @Transactional
    public List<BookDto> getBooksFromShelf(int shelfId, int userId) {
        var shelf = getShelf(shelfId, userId);
        var booksInShelf = shelf.getBooks();

        return booksInShelf.stream().map(book -> BookDto.from(book)).toList();
    }

    @Transactional
    public List<BookDto> addBookToShelf(int shelfId, BookDto bookDto, int userId) {
        var shelf = getShelf(shelfId, userId);
        var booksInShelf = shelf.getBooks();
        var requestedBook = Book.from(bookDto);

        bookService.add(requestedBook);

        booksInShelf.add(requestedBook);
        shelfRepo.save(shelf);

        return booksInShelf.stream().map(book -> BookDto.from(book)).toList();
    }

    @Transactional
    public List<BookDto> removeBookFromShelf(int shelfId, int bookId, int userId) {
        var shelf = getShelf(shelfId, userId);
        var bookToDelete = bookService.getBook(bookId);

        var bookRemoved = shelf.getBooks().remove(bookToDelete);

        if (!bookRemoved) {
            throw new EntityNotFoundException("Shelf does not contain this book");
        }

        shelfRepo.save(shelf);

        bookToDelete.getShelves().remove(shelf);
        bookService.cleanup(bookToDelete);

        return shelf.getBooks().stream().map(book -> BookDto.from(book)).toList();
    }

    @Transactional
    public Shelf getShelf(int shelfId, int userId) {
        var shelf =
                shelfRepo
                        .findById(shelfId)
                        .orElseThrow(() -> new EntityNotFoundException("Shelf does not exist"));
        if (shelf.getOwner().getId() != userId) {
            throw new EntityNotFoundException("Shelf does not exist for this user");
        }
        return shelf;
    }
}
