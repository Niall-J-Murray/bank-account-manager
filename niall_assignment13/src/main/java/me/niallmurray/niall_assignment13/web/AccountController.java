package me.niallmurray.niall_assignment13.web;

import me.niallmurray.niall_assignment13.domain.Account;
import me.niallmurray.niall_assignment13.domain.User;
import me.niallmurray.niall_assignment13.service.AccountService;
import me.niallmurray.niall_assignment13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    UserService userService;

    @PostMapping("/user/{userId}/accounts")
    public String postCreateAccount(@PathVariable Long userId, ModelMap model) {
        User user = userService.findById(userId);
        Account account = accountService.createAccount(user);
        model.addAttribute("user", user);
        model.addAttribute("account", account);

        return "redirect:/user/" + userId + "/accounts/" + account.getAccountId();
    }

    @GetMapping("/user/{userId}/accounts/{accountId}")
    public String getUserAccount(@PathVariable Long userId, @PathVariable Long accountId, ModelMap model) {
        User user = userService.findById(userId);
        Account account = accountService.findAccountById(accountId);
        model.addAttribute("user", user);
        model.addAttribute("account", account);

        return "accounts";
    }

    @PostMapping("user/{userId}/accounts/{accountId}")
    public String changeUserAccountName(@PathVariable Long userId, @PathVariable Long accountId, Account account) {
        User user = userService.findById(userId);
        accountService.updateAccount(user, account);

        return "redirect:/user/" + userId + "/accounts/" + accountId;
    }
}
