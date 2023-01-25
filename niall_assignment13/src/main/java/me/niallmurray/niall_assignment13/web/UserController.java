package me.niallmurray.niall_assignment13.web;

import me.niallmurray.niall_assignment13.domain.User;
import me.niallmurray.niall_assignment13.service.AccountService;
import me.niallmurray.niall_assignment13.service.AddressService;
import me.niallmurray.niall_assignment13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/register")
    public String getCreateUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String postCreateUser(User user) {
        userService.saveUser(user);
        return "redirect:/register";
    }

    @GetMapping("/users")
    public String getAllUsers(ModelMap model) {
        Set<User> users = userService.findAll();
        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping("/users/{userId}")
    public String getUsers(@PathVariable Long userId) {
        return "redirect:/user/" + userId;
    }

    @GetMapping("/user/{userId}")
    public String getUser(@PathVariable Long userId, ModelMap model) {
        User user = userService.findById(userId);
        if(userService.findById(userId).equals(new User())){
            return "redirect:/users";
        }
        model.addAttribute("user", user);

        return "user";
    }

    @PostMapping("/user/{userId}")
    public String postUpdateUser(@PathVariable Long userId, User user) {
        userService.updateUser(user);
        addressService.updateAddress(user);
        return "redirect:/user/" + userId;
    }

    @PostMapping("/user/{userId}/delete")
    public String deleteOneUser(@PathVariable Long userId) {
        userService.delete(userId);
        return "redirect:/users";
    }
}
