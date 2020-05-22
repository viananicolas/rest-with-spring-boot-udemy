package br.com.spring.restwithspringbootudemy.services;

import br.com.spring.restwithspringbootudemy.controller.PersonController;
import br.com.spring.restwithspringbootudemy.converter.DozerConverter;
import br.com.spring.restwithspringbootudemy.data.model.Person;
import br.com.spring.restwithspringbootudemy.data.viewmodel.PersonViewModel;
import br.com.spring.restwithspringbootudemy.exception.ResourceNotFoundException;
import br.com.spring.restwithspringbootudemy.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {

    @Autowired
    private IPersonRepository personRepository;

    public PersonViewModel create(PersonViewModel person) {
        var personViewModel = DozerConverter
                .parseObject(personRepository
                        .save(DozerConverter.parseObject(person, Person.class)), PersonViewModel.class);
        personViewModel.add(linkTo(methodOn(PersonController.class).findById(personViewModel.getKey())).withSelfRel());
        return personViewModel;
    }

    public PersonViewModel update(PersonViewModel person) {
        if (person.getKey() == null)
            throw new IllegalArgumentException("id is null");

        var entity = personRepository
                .findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No record found"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        var personViewModel = DozerConverter
                .parseObject(personRepository.save(entity), PersonViewModel.class);
        personViewModel.add(linkTo(methodOn(PersonController.class).findById(personViewModel.getKey())).withSelfRel());
        return personViewModel;
    }


    public PersonViewModel findById(Long id) {
        var personViewModel = DozerConverter.parseObject(personRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found")), PersonViewModel.class);

        personViewModel.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personViewModel;
    }

    public List<PersonViewModel> findAll() {
        var people = DozerConverter.parseListObject(personRepository.findAll(), PersonViewModel.class);
        people.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return people;
    }

    public void delete(Long id) {
        if (id == null)
            throw new IllegalArgumentException("id is null");

        var entity = personRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found"));

        personRepository.delete(entity);
    }
}
