package br.com.spring.restwithspringbootudemy.repository;

import br.com.spring.restwithspringbootudemy.data.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {

}
