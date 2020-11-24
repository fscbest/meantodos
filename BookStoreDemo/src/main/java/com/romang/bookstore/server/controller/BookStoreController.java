package com.romang.bookstore.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.romang.bookstore.server.model.Book;
import com.romang.bookstore.server.service.BookStoreService;

@RestController
public class BookStoreController {
	
	@Autowired
	private BookStoreService service;
	
	@GetMapping("/getbooklist")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Book> fetchBookList() {		
		List<Book> books = new ArrayList<Book>();
		books = service.fetchBookList();
		return books;		
	}
	
	@PostMapping("/addbook")
	@CrossOrigin(origins = "http://localhost:4200")
	public Book saveBook(@RequestBody Book book) {		
		return service.saveBook(book);
	}
	
	@PostMapping("/updatebook")
	@CrossOrigin(origins = "http://localhost:4200")
	public Book updateBook(@RequestBody Book book) {	
		
		return service.saveBook(book);
	}
	
	@PostMapping("/buybook")
	@CrossOrigin(origins = "http://localhost:4200")
	public Book buyBook(@RequestBody Book book) {	
		
		return service.buyBook(book);
	}
	
	@DeleteMapping("/deletebookbyid/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean deleteBookById(@PathVariable Long id) {		
		return service.deleteBookById(id);
	}

	@GetMapping("/getbookbyid/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Book fetchBookById(@PathVariable Long id) {		
		return service.fetchBookById(id).get();		
	}
	
	@GetMapping("/getbookbyname/{name}")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Book> fetchBookByName(@PathVariable String name) {		
		List<Book> books = service.fetchBookByName(name);
		return books;		
	}
}
