package br.com.spring.restwithspringbootudemy.mock;
import br.com.spring.restwithspringbootudemy.data.model.Person;
import br.com.spring.restwithspringbootudemy.data.viewmodel.PersonViewModel;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {

    public Person mockEntity() {
        return mockEntity(0);
    }

    public PersonViewModel mockViewModel() {
        return mockViewModel(0);
    }

    public List<Person> mockEntityList() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<PersonViewModel> mockViewModelList() {
        List<PersonViewModel> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockViewModel(i));
        }
        return persons;
    }

    private Person mockEntity(Integer number) {
        Person person = new Person();
        person.setAddress("Address Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setId(number.longValue());
        person.setLastName("Last Name Test" + number);
        return person;
    }

    private PersonViewModel mockViewModel(Integer number) {
        PersonViewModel person = new PersonViewModel();
        person.setAddress("Address Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setKey(number.longValue());
        person.setLastName("Last Name Test" + number);
        return person;
    }

}