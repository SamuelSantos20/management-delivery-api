package com.packge.manager.tosam.br.api_management_deliverieso.validations;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.User;
import com.packge.manager.tosam.br.api_management_deliverieso.exceptions.DuplicateRecordException;
import com.packge.manager.tosam.br.api_management_deliverieso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserValidation {

    @Autowired
    private UserService userService;

    public  void validation(User user) {

        if (existsUser(user)){

            throw new DuplicateRecordException("Este Login já se encontra cadastrado no sitema");
        }


    }

    private boolean existsUser(User user) {

        Optional<User> userOptional = userService.GetDetailsEmail(user.getEmail());

        if (userOptional.isEmpty()){

         return false;
        }

         if (user.getId() == null){

            return true;
        }

         return userOptional.map(User :: getId).stream().anyMatch(id -> !id.equals(user.getId()));

    }


}
