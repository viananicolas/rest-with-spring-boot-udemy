package br.com.spring.restwithspringbootudemy.services;

 import br.com.spring.restwithspringbootudemy.converter.DozerConverter;
 import br.com.spring.restwithspringbootudemy.data.model.Person;
 import br.com.spring.restwithspringbootudemy.data.viewmodel.PersonViewModel;
 import br.com.spring.restwithspringbootudemy.exception.ResourceNotFoundException;
 import br.com.spring.restwithspringbootudemy.repository.IPersonRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import java.util.List;

@Service
public class PersonService {

    @Autowired
    private IPersonRepository personRepository;

    public PersonViewModel create(PersonViewModel person){
        return DozerConverter
                .parseObject(personRepository
                        .save(DozerConverter.parseObject(person, Person.class)), PersonViewModel.class);
    }

    public PersonViewModel update(PersonViewModel person){
        if(person.getId() == null)
            throw new IllegalArgumentException("id is null");

        var entity = personRepository
                .findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No record found"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return DozerConverter
                .parseObject(personRepository.save(entity), PersonViewModel.class);
    }


    public PersonViewModel findById(Long id){
        return DozerConverter.parseObject(personRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found")), PersonViewModel.class);
    }

    public List<PersonViewModel> findAll(){
        return DozerConverter.parseListObject(personRepository.findAll(), PersonViewModel.class);
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
