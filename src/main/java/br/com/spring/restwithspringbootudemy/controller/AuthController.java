package br.com.spring.restwithspringbootudemy.controller;

import br.com.spring.restwithspringbootudemy.data.viewmodel.PersonViewModel;
import br.com.spring.restwithspringbootudemy.repository.IUserRepository;
import br.com.spring.restwithspringbootudemy.security.AccountCredentialsViewModel;
import br.com.spring.restwithspringbootudemy.security.jwt.JwtTokenProvider;
import io.swagger.annotations.ApiOperation;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    IUserRepository userRepository;

    @ApiOperation(value = "Authenticate user")
    @PostMapping(value = "/signin",produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<HashMap<Object, Object>> create(@RequestBody AccountCredentialsViewModel accountCredentialsViewModel) {
        try{
            authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    accountCredentialsViewModel.getUserName(),
                                    accountCredentialsViewModel.getPassword()));

            var user = userRepository.findByUsername(accountCredentialsViewModel.getUserName());
            var token = "";
            if(user != null)
                token = jwtTokenProvider.createToken(accountCredentialsViewModel.getUserName(), user.getRoles());
            else
                throw new UsernameNotFoundException("User not found");

            var model = new HashMap<>();
            model.put("username", accountCredentialsViewModel.getUserName());
            model.put("token", token);
            return ok(model);
        }
        catch (Exception e){
            throw new BadCredentialsException("Invalid username/password");
        }
    }
}
