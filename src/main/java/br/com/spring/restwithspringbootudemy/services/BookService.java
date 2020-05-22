package br.com.spring.restwithspringbootudemy.services;

import br.com.spring.restwithspringbootudemy.converter.DozerConverter;
import br.com.spring.restwithspringbootudemy.data.model.Book;
import br.com.spring.restwithspringbootudemy.exception.ResourceNotFoundException;
import br.com.spring.restwithspringbootudemy.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spring.restwithspringbootudemy.data.viewmodel.BookViewModel;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private IBookRepository personRepository;

    public BookViewModel create(BookViewModel book){
        return DozerConverter
                .parseObject(personRepository
                        .save(DozerConverter.parseObject(book, Book.class)), BookViewModel.class);
    }

    public BookViewModel update(BookViewModel book){
        if(book.getKey() == null)
            throw new IllegalArgumentException("id is null");

        var entity = personRepository
                .findById(book.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No record found"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        return DozerConverter
                .parseObject(personRepository.save(entity), BookViewModel.class);
    }


    public BookViewModel findById(Long id){
        return DozerConverter.parseObject(personRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found")), BookViewModel.class);
    }

    public List<BookViewModel> findAll(){
        return DozerConverter.parseListObject(personRepository.findAll(), BookViewModel.class);
    }

    public void delete(Long id){
        if(id == null)
            throw new IllegalArgumentException("id is null");

        var entity = personRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found"));

        personRepository.delete(entity);
    }
}
