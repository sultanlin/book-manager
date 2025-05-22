package com.sultanlinjawi.library.dto;

import com.sultanlinjawi.library.models.Shelf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShelfDto {
    private int id;
    private String name;

    public static ShelfDto from(Shelf shelf) {
        return new ShelfDto(shelf.getId(), shelf.getName());
    }
}
