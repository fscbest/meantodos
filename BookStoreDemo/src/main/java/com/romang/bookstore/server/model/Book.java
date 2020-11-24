package com.romang.bookstore.server.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	
	@Version
	@Column(name = "version")
	private Long version;
	
	@Column(name = "name", nullable = false)
	@Basic(optional = false)
	private String name;
	
	@Column(name = "author", nullable = false)
	@Basic(optional = false)
	private String author;
	
	@Column(name = "price", nullable = false)
	@Basic(optional = false)
	private int price;
	
	@Column(name = "quantity", nullable = false)
	@Basic(optional = false)
	private int quantity;
	
	public Book() {	}
	
	public Book(Long id, Long version, String name, String author, int price, int quantity) {
		super();
		this.id = id;
		this.version = version;
		this.name = name;
		this.author = author;
		this.price = price;
		this.quantity = quantity;
	}
	
	public Book(String name, String author, int price, int quantity) {
		super();
		this.name = name;
		this.author = author;
		this.price = price;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}
	
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public float getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", author=" + author + ", price=" + price + ", quantity=" + quantity + "]";
	}
	
	
	
	
	
}
