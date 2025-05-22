package com.sultanlinjawi.library.repos;

import com.sultanlinjawi.library.models.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {}
