package mira.space.kameleoon.services.impl;

import mira.space.kameleoon.exceptions.PropertyBadRequestException;
import mira.space.kameleoon.exceptions.UserPropertyAlreadyExistException;
import mira.space.kameleoon.models.User;
import mira.space.kameleoon.models.repositories.UserRepository;
import mira.space.kameleoon.services.UserService;
import mira.space.kameleoon.utils.ValidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Checks user properties for valid state
     *
     * @throws UserPropertyAlreadyExistException if unique property already exist in db
     * @return true if all properties are valid
     */
    @Override
    public boolean isUserValid(User user) {
        if (user == null) {
            return false;
        }
        ValidUtils.checkValid("name", user.getName());
        ValidUtils.checkValid("password", user.getPassword());
        Optional<String> loadedPassword = userRepository.findPassword(user.getPassword());
        if (loadedPassword.isPresent()) { // if password exist means uniqueness violation
            throw new UserPropertyAlreadyExistException(user.getPassword());
        }
        if (!ValidUtils.isEmailValid(user.getEmail())) {
            throw new PropertyBadRequestException("email");
        } else {
            Optional<String> loadedEmail = userRepository.findEmail(user.getEmail());
            if (loadedEmail.isPresent()) { // if email exist means uniqueness violation
                throw new UserPropertyAlreadyExistException(user.getEmail());
            }
        }
        return true;
    }
}
