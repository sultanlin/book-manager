package com.sultanlinjawi.library.services;

import com.sultanlinjawi.library.dto.ShelfDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShelfService {
    private final UserService userService;

    @Transactional
    public List<ShelfDto> getShelves(int userId) {
        var user = userService.getUserById(userId);
        var shelves = user.getShelves();
        var shelvesDto = shelves.stream().map((shelf) -> ShelfDto.from(shelf)).toList();
        return shelvesDto;
    }
}
