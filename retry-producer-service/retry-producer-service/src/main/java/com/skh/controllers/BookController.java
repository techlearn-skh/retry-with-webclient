/*
package com.skh.controllers;

import com.skh.models.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // GET - Retrieve all books
    @GetMapping
    public ResponseEntity<ApiResponse<List<Book>>> getAllBooks() {
        List<Book> books = bookService.findAll();
        ApiResponse<List<Book>> response = new ApiResponse<>("success", books);
        return ResponseEntity.ok(response);
    }

    // GET - Retrieve single book
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> getBook(@PathVariable Long id) {
        return bookService.findById(id)
                .map(book -> {
                    ApiResponse<Book> response = new ApiResponse<>("success", book);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>("error", "Book not found")));
    }

    // POST - Create new book
    @PostMapping
    public ResponseEntity<ApiResponse<Book>> createBook(@Valid @RequestBody Book book) {
        Book savedBook = bookService.save(book);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBook.getId())
                .toUri();

        ApiResponse<Book> response = new ApiResponse<>("success", savedBook);
        return ResponseEntity.created(location).body(response);
    }

    // PUT - Update book
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> updateBook(@PathVariable Long id,
                                                        @Valid @RequestBody Book book) {
        if (!bookService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        book.setId(id);
        Book updatedBook = bookService.save(book);
        ApiResponse<Book> response = new ApiResponse<>("success", updatedBook);
        return ResponseEntity.ok(response);
    }

    // DELETE - Delete book
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable Long id) {
        if (!bookService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // PATCH - Partial update
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> partialUpdate(@PathVariable Long id,
                                                           @RequestBody Map<String, Object> updates) {
        try {
            Book updatedBook = bookService.partialUpdate(id, updates);
            ApiResponse<Book> response = new ApiResponse<>("success", updatedBook);
            return ResponseEntity.ok(response);
        } catch (BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

*/
