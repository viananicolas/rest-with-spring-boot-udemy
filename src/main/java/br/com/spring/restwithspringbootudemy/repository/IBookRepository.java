package br.com.spring.restwithspringbootudemy.repository;

import br.com.spring.restwithspringbootudemy.data.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {

}
