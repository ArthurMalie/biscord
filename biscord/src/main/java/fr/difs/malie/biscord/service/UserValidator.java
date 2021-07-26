package fr.difs.malie.biscord.service;

import fr.difs.malie.biscord.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    /**
     * Vérifie que le UserValidator peut traiter un objet d'une certaine classe.
     * @param aClass la classe à tester
     * @return true si la classe aClass peut être traitée par UserValidator,
     *          false sinon.
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    /**
     *  Vérifie la validité de l'ensemble des valeurs attribuées à un User.
     *      - si le pseudo est déjà utilisé par un autre utilisateur
     *      - si le pseudo et le mot de passe choisit sont valides (longueur)
     *      - si la confirmation du mot de passe correspond avec le mot de passe
     * @param o l'utilisateur à valider
     * @param errors les erreurs soulevées suite à la non conformité de certains attributs de l'utilisateur o
     */
    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 2 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 5 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
