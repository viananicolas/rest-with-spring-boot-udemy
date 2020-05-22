package br.com.spring.restwithspringbootudemy.services;

import br.com.spring.restwithspringbootudemy.converter.DozerConverter;
import br.com.spring.restwithspringbootudemy.data.model.Person;
import br.com.spring.restwithspringbootudemy.data.viewmodel.PersonViewModel;
import br.com.spring.restwithspringbootudemy.exception.ResourceNotFoundException;
import br.com.spring.restwithspringbootudemy.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    /*public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }*/

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(userName);
        if (user == null)
            throw new UsernameNotFoundException("Username " + userName + " not found");
        return user;
    }
}
