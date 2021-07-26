package fr.difs.malie.biscord.web;

import fr.difs.malie.biscord.data.MessageDAO;
import fr.difs.malie.biscord.data.UserDAO;
import fr.difs.malie.biscord.model.Message;
import fr.difs.malie.biscord.model.User;
import fr.difs.malie.biscord.service.SecurityService;
import fr.difs.malie.biscord.service.UserService;
import fr.difs.malie.biscord.service.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private MessageDAO messageDAO;
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/messagerie")
    public String showMessages(Model model, Principal principal) {
        List<Message> messages = messageDAO.findAll();
        model.addAttribute("msgList", messages);
        model.addAttribute("newMessage", new Message());
        model.addAttribute("currentUser", principal.getName());
        return "messagerie";
    }

    @PostMapping("/messagerie")
    public String postMessage(@ModelAttribute("newMessage") Message newMessage, Principal principal) {
        User currentUser = userDAO.findUserByUsername(principal.getName());
        Message msg = new Message(currentUser, newMessage.getContent());
        messageDAO.save(msg);
        return "redirect:/messagerie";
    }

    @PostMapping("/messagerie/delete/{id}")
    public String deleteMessage(@PathVariable("id") int id) {
        messageDAO.deleteById(id);
        return "redirect:/messagerie";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Pseudo ou mot de passe incorrect");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping("/")
    public String welcome(Model model) {
        return "welcome";
    }


}
