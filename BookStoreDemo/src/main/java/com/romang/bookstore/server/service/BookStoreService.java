package com.romang.bookstore.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.romang.bookstore.server.model.Book;
import com.romang.bookstore.server.repo.IBookStoreRepo;

@Service
public class BookStoreService {
	
	@Autowired
	IBookStoreRepo repo;

	public List<Book> fetchBookList() {
		return repo.findAll();		
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public Book saveBook(Book updatedBook) {
		return repo.save(updatedBook);
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public Book buyBook(Book updatedBook) {
		Optional<Book> book = repo.findById(updatedBook.getId());
		if(book.isPresent()) {
			if(book.get().getQuantity() > 0) {
				book.get().setQuantity(book.get().getQuantity() - 1);
				try {
					return repo.save(book.get());
				} catch (ObjectOptimisticLockingFailureException e) {
					return repo.save(book.get());
				}
			}

		}
		return null;
		
	}
	
	public boolean deleteBookById(Long id) {
		boolean result = false;
		try {
			repo.deleteById(id);
			result = true;
		} catch (Exception  e) {
			
		} 
		
		return result;
	}
	
	public Optional<Book> fetchBookById(Long id) {
		Optional<Book> book = repo.findById(id);
		return book.isPresent() ? book : null;
	}
	
	public List<Book> fetchBookByName(String name) {
		return repo.findByNameContainingIgnoreCase(name);
	}
}
