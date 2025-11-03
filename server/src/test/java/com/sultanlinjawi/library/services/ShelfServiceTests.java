package com.sultanlinjawi.library.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sultanlinjawi.library.dto.ShelfDto;
import com.sultanlinjawi.library.models.Shelf;
import com.sultanlinjawi.library.models.User;
import com.sultanlinjawi.library.repos.ShelfRepo;

import jakarta.persistence.EntityNotFoundException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ShelfServiceTests {
    @Mock private ShelfRepo shelfRepo;
    @Mock private UserService userService;
    @Mock private BookService bookService;

    @InjectMocks private ShelfService service;

    @Test
    @DisplayName("Create shelf, returns shelf after saving (happy ending)")
    public void ShelfAddedHappyEnding() {
        var userId = 1;
        var shelfName = "Test Shelf";
        String username = "tester 1";
        var user = User.builder().id(userId).username(username).password("pass").build();
        var shelf = Shelf.builder().name(shelfName).owner(user).build();
        var shelf2 = Shelf.builder().name("different name").owner(user).build();
        user.getShelves().add(shelf2);

        when(userService.findUserByUsername(username)).thenReturn(user);
        when(shelfRepo.save(Mockito.any())).thenReturn(shelf);

        var expected = ShelfDto.from(shelf);
        var actual = service.createShelf(shelfName, username);

        verify(shelfRepo).save(Mockito.any());
        Assertions.assertThat(actual).isNotNull();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Create shelf, cannot add a shelf if the user has a shelf with the same name")
    public void ThrowExceptionIfShelfNameIsTakenForUser() {
        var userId = 1;
        var shelfName = "Test Shelf";
        String username = "tester 1";
        var user = User.builder().id(userId).username(username).password("pass").build();
        var shelf = Shelf.builder().name(shelfName).owner(user).build();
        user.getShelves().add(shelf);

        when(userService.findUserByUsername(username)).thenReturn(user);

        var expected = "Shelf with this name already exists for this user";
        var actual =
                assertThrows(
                                IllegalArgumentException.class,
                                () -> service.createShelf(shelfName, username))
                        .getMessage();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get all shelves, returns all shelves from user (happy ending)")
    public void GetShelvesHappyEnding() {
        var userId = 1;
        String username = "tester 1";
        var user = User.builder().id(userId).username(username).password("pass").build();
        var shelf = Shelf.builder().name("Test Shelf").owner(user).build();
        var shelf2 = Shelf.builder().name("different name").owner(user).build();
        user.getShelves().add(shelf);
        user.getShelves().add(shelf2);

        when(userService.findUserByUsername(username)).thenReturn(user);

        var expected = List.of(ShelfDto.from(shelf), ShelfDto.from(shelf2));
        var actual = service.getShelves(username);

        Assertions.assertThat(actual).isNotNull();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get all shelves, empty list when user has no shelves")
    public void ReturnEmptyListIfUserHasNoShelves() {
        var userId = 1;
        String username = "tester 1";
        var user = User.builder().id(userId).username(username).password("pass").build();

        when(userService.findUserByUsername(username)).thenReturn(user);

        var expected = List.of();
        var actual = service.getShelves(username);

        Assertions.assertThat(actual).isNotNull();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get shelf(utility function), return shelf with matching id (happy ending)")
    public void GetShelfUtilityHappyEnding() {
        var userId = 1;
        var shelfId = 10;
        String username = "tester 1";
        var user = User.builder().id(userId).username(username).password("pass").build();
        var shelf = Shelf.builder().id(shelfId).name("read now").owner(user).build();

        when(shelfRepo.findById(shelfId)).thenReturn(Optional.of(shelf));

        var expected = shelf;
        var actual = service.getShelf(shelfId, username);

        Assertions.assertThat(actual).isNotNull();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get shelf(utility function), throw exception when shelf does not belong to user")
    public void ThrowErrorWhenShelfDoesNotBelongToUser() {
        var shelfId = 10;
        var username = "sultan";
        var otherUser = User.builder().id(55).username("tester 1").password("pass").build();
        var shelf = Shelf.builder().id(shelfId).name("read now").owner(otherUser).build();

        when(shelfRepo.findById(shelfId)).thenReturn(Optional.of(shelf));

        var expected = "Shelf does not exist for this user";
        var actual =
                assertThrows(
                                EntityNotFoundException.class,
                                () -> service.getShelf(shelfId, username))
                        .getMessage();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get shelf(utility function), throw exception when no shelf exists with this id")
    public void ThrowErrorWhenShelfDoesNotExist() {
        var username = "tester";

        when(shelfRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        var expected = "Shelf does not exist";
        var actual =
                assertThrows(
                                EntityNotFoundException.class,
                                () -> {
                                    service.getShelf(Mockito.anyInt(), username);
                                })
                        .getMessage();

        assertEquals(expected, actual);
    }

    // getBooksFromShelf
    // addBookToShelf
    // removeBookFromShelf

}
