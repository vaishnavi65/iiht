package com.programcreek.helloworld.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.programcreek.helloworld.model.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long>{
	
	@Query("SELECT t.title FROM Book t where t.title = :title") 
    String findByTitle(@Param("title") String title);
	
	List<Book> findAll();
}
