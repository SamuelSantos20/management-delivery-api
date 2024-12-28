package com.packge.manager.tosam.br.api_management_deliverieso.service;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.User;
import com.packge.manager.tosam.br.api_management_deliverieso.repository.UserRepository;
import com.packge.manager.tosam.br.api_management_deliverieso.validations.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    //private final UserValidation userValidation;

    public void save(User user) {

        System.out.println(user.getPassword());

      //  userValidation.validation(user);

        Optional<User> userOptional = Optional.ofNullable(user);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Usuaraio ou senha não informados!");
        }

        User userEntity = userOptional.get();

        String encode = passwordEncoder.encode(userEntity.getPassword());

        userEntity.setPassword(encode);

        userRepository.save(userEntity);
    }

    public Optional<User> GetDetails(UUID id) {

        return userRepository.findById(id);

    }

    public Optional<User> GetDetailsEmail(String login) {

        return userRepository.findByEmail(login);

    }


    public void Update(User user) {

        //userValidation.validation(user);
        userRepository.save(user);

    }


    public  void Delete(User user) {

        userRepository.delete(user);

    }


}
