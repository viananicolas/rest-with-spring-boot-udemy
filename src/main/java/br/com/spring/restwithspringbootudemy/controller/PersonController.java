package br.com.spring.restwithspringbootudemy.controller;

import br.com.spring.restwithspringbootudemy.data.viewmodel.PersonViewModel;
import br.com.spring.restwithspringbootudemy.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(value = "/{id}")
    public PersonViewModel findById(@PathVariable("id") Long id){
        return personService.findById(id);
    }

    @GetMapping
    public List<PersonViewModel> findAll(){
        return personService.findAll();
    }

    @PostMapping
    public PersonViewModel create(@RequestBody PersonViewModel person){
        return personService.create(person);
    }

    @PutMapping
    public PersonViewModel update(@RequestBody PersonViewModel person){
        return personService.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        personService.delete(id);
        return ResponseEntity.ok().build();
    }
}
