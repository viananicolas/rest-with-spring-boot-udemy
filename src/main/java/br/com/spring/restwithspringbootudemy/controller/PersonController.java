package br.com.spring.restwithspringbootudemy.controller;

import br.com.spring.restwithspringbootudemy.data.viewmodel.PersonViewModel;
import br.com.spring.restwithspringbootudemy.services.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Person Endpoint", description = "API to handle people", tags = {"Person Endpoint"})
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @ApiOperation(value = "Find person by Id")
    @GetMapping(value = "/{id}",
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public PersonViewModel findById(@PathVariable("id") Long id) {
        return personService.findById(id);
    }

    @ApiOperation(value = "Find all people")
    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<PersonViewModel> findAll() {
        return personService.findAll();
    }

    @ApiOperation(value = "Create a new person")
    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PersonViewModel create(@RequestBody PersonViewModel person) {
        return personService.create(person);
    }

    @ApiOperation(value = "Update a person by Id")
    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PersonViewModel update(@RequestBody PersonViewModel person) {
        return personService.update(person);
    }

    @ApiOperation(value = "Delete a person by Id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        personService.delete(id);
        return ResponseEntity.ok().build();
    }
}
