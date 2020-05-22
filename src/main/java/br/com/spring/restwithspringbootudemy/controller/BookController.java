package br.com.spring.restwithspringbootudemy.controller;

import br.com.spring.restwithspringbootudemy.data.viewmodel.BookViewModel;
import br.com.spring.restwithspringbootudemy.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public BookViewModel findById(@PathVariable("id") Long id) {
        var bookViewModel = bookService.findById(id);
        bookViewModel.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return bookViewModel;
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<BookViewModel> findAll() {
        var books = bookService.findAll();
        books.forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
        return books;
    }

    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public BookViewModel create(@RequestBody BookViewModel book) {
        var bookViewModel = bookService.create(book);
        bookViewModel.add(linkTo(methodOn(BookController.class).findById(bookViewModel.getKey())).withSelfRel());
        return bookViewModel;
    }

    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public BookViewModel update(@RequestBody BookViewModel book) {
        var bookViewModel = bookService.update(book);
        bookViewModel.add(linkTo(methodOn(BookController.class).findById(bookViewModel.getKey())).withSelfRel());
        return bookViewModel;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        bookService.delete(id);
        return ResponseEntity.ok().build();
    }
}
