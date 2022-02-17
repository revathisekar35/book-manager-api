package com.techreturners.bookmanager.controller;

import com.techreturners.bookmanager.exception.ApiRequestException;
import com.techreturners.bookmanager.model.Book;
import com.techreturners.bookmanager.service.BookManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/book")
public class BookManagerController {

	@Autowired
	BookManagerService bookManagerService;

	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> books = bookManagerService.getAllBooks();
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@GetMapping({ "/{bookId}" })
	public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
		try {
			return new ResponseEntity<>(bookManagerService.getBookById(bookId), HttpStatus.OK);
		} catch (NoSuchElementException e) {
			throw new ApiRequestException("Oops Request book is not available");
		}
	}

	@PostMapping
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		try {
			Book newBook = bookManagerService.insertBook(book);
			HttpHeaders httpHeaders = new HttpHeaders();
			System.out.println("newBook---" + newBook);
			httpHeaders.add("book", "/api/v1/book/" + newBook.getId().toString());
			return new ResponseEntity<>(newBook, httpHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ApiRequestException("Input Json is not valid");
		}
	}

	// User Story 4 - Update Book By Id Solution
	@PutMapping({ "/{bookId}" })
	public ResponseEntity<Book> updateBookById(@PathVariable("bookId") Long bookId, @RequestBody Book book) {
		try {
			bookManagerService.updateBookById(bookId, book);
			return new ResponseEntity<>(bookManagerService.getBookById(bookId), HttpStatus.OK);
		} catch (NoSuchElementException e) {
			throw new ApiRequestException("Oops Request book is not available");
		}
	}

	@DeleteMapping({ "/{bookId}" })
	public ResponseEntity<Book> deleteBookById(@PathVariable Long bookId) {
		try {
			bookManagerService.deleteBookById(bookId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (EmptyResultDataAccessException e) {
			throw new ApiRequestException("Oops Request book is not available");
		}
	}

}
