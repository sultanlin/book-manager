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
        var existingShelf =
                user.getShelves().stream().filter(s -> s.getName().equals(shelfName)).findFirst();
        if (existingShelf.isPresent()) {
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
                            book.getShelves().remove(shelf);
                            bookService.cleanup(book);
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
        var booksInShelf = shelf.getBooks();
        var requestedBook = Book.from(bookDto);

        bookService.add(requestedBook);

        booksInShelf.add(requestedBook);
        shelfRepo.save(shelf);

        return booksInShelf.stream().map(book -> BookDto.from(book)).toList();
    }

    @Transactional
    public List<BookDto> removeBookFromShelf(int shelfId, int bookId, String username) {
        var shelf = getShelf(shelfId, username);
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
    public Shelf getShelf(int shelfId, String username) {
        var shelf =
                shelfRepo
                        .findById(shelfId)
                        .orElseThrow(() -> new EntityNotFoundException("Shelf does not exist"));
        System.out.println("shelf owner is: " + shelf.getOwner().getUsername());
        System.out.println("shelf owner is: " + username);
        if (!shelf.getOwner().getUsername().equals(username)) {
            throw new EntityNotFoundException("Shelf does not exist for this user");
        }
        return shelf;
    }
}
