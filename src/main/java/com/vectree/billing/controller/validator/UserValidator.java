package com.vectree.billing.controller.validator;

import com.vectree.billing.domain.User;
import com.vectree.billing.service.UserService;
import com.vectree.billing.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.vectree.billing.utils.Constants.MAX_LENGTH_USERNAME;
import static com.vectree.billing.utils.Constants.MIN_LENGTH_USERNAME;

/**
 * Validates username & password.
 *
 * @version 0.1.
 */
@Component
@PropertySource("/WEB-INF/properties/messages.properties")
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (user.getUsername().length() < MIN_LENGTH_USERNAME || user.getUsername().length() > MAX_LENGTH_USERNAME) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        String email = user.getEmail();
        if (!Strings.emailAddress(email)) {
            errors.rejectValue("email", "Contains.userForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");

        String password = user.getPassword();
        if (!Strings.digitsAndCharactersOnly(password)) {
            errors.rejectValue("password", "Contains.userForm.password");
        }
        if (!user.getConfirmPassword().equals(password)) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }
}
