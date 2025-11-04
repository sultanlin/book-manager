package com.sultanlinjawi.library.shelf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShelfRepo extends JpaRepository<Shelf, Integer> {
    Optional<Shelf> findById(int id);
}
