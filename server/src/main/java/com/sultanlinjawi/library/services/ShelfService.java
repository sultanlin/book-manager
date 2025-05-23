package com.sultanlinjawi.library.services;

import com.sultanlinjawi.library.dto.ShelfDto;
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
    public Shelf getShelf(int shelfId, int userId) {
        var shelf =
                shelfRepo
                        .findById(shelfId)
                        .orElseThrow(() -> new EntityNotFoundException("Shelf does not exist"));
        if (shelf.getOwner().getId() != userId) {
            // TODO: Consider different exception
            throw new EntityNotFoundException("Shelf does not exist for this user");
        }
        return shelf;
    }
}
