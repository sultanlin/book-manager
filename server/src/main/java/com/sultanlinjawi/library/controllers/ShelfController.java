package com.sultanlinjawi.library.controllers;

import com.sultanlinjawi.library.dto.ShelfDto;
import com.sultanlinjawi.library.security.UserDetailsImpl;
import com.sultanlinjawi.library.services.ShelfService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
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
}
