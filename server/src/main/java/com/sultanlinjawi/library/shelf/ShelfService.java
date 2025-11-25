package com.sultanlinjawi.library.shelf;

import com.sultanlinjawi.library.book.Book;
import com.sultanlinjawi.library.book.BookDto;
import com.sultanlinjawi.library.book.BookService;
import com.sultanlinjawi.library.user.UserService;

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
    public List<ShelfDto> getShelves(String username) {
        return shelfRepo.findByOwner_Username(username).stream()
                .map((shelf) -> ShelfDto.from(shelf))
                .toList();
    }

    @Transactional
    public ShelfDto createShelf(String shelfName, String username) {
        var user = userService.findUserByUsername(username);
        var shelf = Shelf.builder().name(shelfName).owner(user).build();
        if (user.getShelves().stream().anyMatch(s -> s.getName().equals(shelfName))) {
            throw new IllegalArgumentException("Shelf with this name already exists for this user");
        }
        return ShelfDto.from(shelfRepo.save(shelf));
    }

    @Transactional
    public ShelfDto updateShelf(int shelfId, String shelfName, String username) {
        var shelf = getShelf(shelfId, username);
        shelf.setName(shelfName);
        return ShelfDto.from(shelfRepo.save(shelf));
    }

    @Transactional
    public void deleteShelf(int shelfId, String username) {
        var shelf = getShelf(shelfId, username);

        shelf.getBooks().stream()
                .forEach(
                        (book) -> {
                            switch (book.removeShelf(shelf)) {
                                case EMPTY -> {
                                    bookService.cleanup(book);
                                }
                                case REMOVED ->
                                        log.debug("DELETE SHELF: removing " + book.getTitle());
                                case NOT_FOUND -> log.debug("DELETE SHELF: Book not found?!?");
                            }
                        });

        shelfRepo.delete(shelf);
    }

    @Transactional
    public List<BookDto> getBooksInShelf(int shelfId, String username) {
        return getShelf(shelfId, username).getBooks().stream()
                .map(book -> BookDto.from(book))
                .toList();
    }

    @Transactional
    public List<BookDto> addBookToShelf(int shelfId, BookDto bookDto, String username) {
        var shelf = getShelf(shelfId, username);
        var requestedBook = Book.from(bookDto);

        bookService.add(requestedBook);
        shelf.addBook(requestedBook);
        shelfRepo.save(shelf);

        return shelf.getBooks().stream().map(book -> BookDto.from(book)).toList();
    }

    @Transactional
    public List<BookDto> removeBookFromShelf(int shelfId, int bookId, String username) {
        var shelf = getShelf(shelfId, username);
        var book = bookService.getBook(bookId);

        switch (book.removeShelf(shelf)) {
            case EMPTY -> {
                log.debug("REMOVE BOOK FROM SHELF: Book has no shelf, removing book from DB");
                bookService.cleanup(book);
            }
            case REMOVED -> log.debug("REMOVE BOOK FROM SHELF: Removing book " + book.getTitle());
            case NOT_FOUND -> throw new EntityNotFoundException("Book not found in shelf.");
        }

        shelf.removeBook(book);
        shelfRepo.save(shelf);

        return shelf.getBooks().stream().map(b -> BookDto.from(b)).toList();
    }

    @Transactional
    public Shelf getShelf(int shelfId, String username) {
        var shelf =
                shelfRepo
                        .findById(shelfId)
                        .orElseThrow(() -> new EntityNotFoundException("Shelf does not exist"));
        if (!shelf.getOwner().getUsername().equals(username)) {
            throw new EntityNotFoundException("Shelf does not exist for this user");
        }
        return shelf;
    }
}
