package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Book;


@Repository("BookRepository")
public interface BookRepository extends JpaRepository<Book, Long>{

}
