package com.romang.bookstore.server.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.romang.bookstore.server.model.Book;

public interface IBookStoreRepo extends JpaRepository <Book, Long>, QueryByExampleExecutor<Book>{

	List<Book> findByNameContainingIgnoreCase(String name);
	List<Book> findByAuthorContainingIgnoreCase(String author);

}
