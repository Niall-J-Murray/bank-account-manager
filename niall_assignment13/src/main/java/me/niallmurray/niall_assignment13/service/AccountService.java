package me.niallmurray.niall_assignment13.service;

import me.niallmurray.niall_assignment13.domain.Account;
import me.niallmurray.niall_assignment13.domain.User;
import me.niallmurray.niall_assignment13.repository.AccountRepository;
import me.niallmurray.niall_assignment13.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private UserRepository userRepo;

    public Account createAccount(User user) {
        Account account = new Account();
        account.setAccountName("Account #" + (user.getAccounts().size() + 1));
        account.getUsers().add(user);
        user.getAccounts().add(account);

        return accountRepo.save(account);
    }

    public Account updateAccount(User user, Account account) {
        List<Account> allUserAccounts = user.getAccounts();
        // If user changes account name to a name that already exists "A" is appended.
        for (Account acc : allUserAccounts) {
            if ((acc.getAccountName().equals(account.getAccountName())
                    && (!acc.getAccountId().equals(account.getAccountId())))) {
                account.setAccountName(account.getAccountName() + "A");
            } else
                account.setAccountName(account.getAccountName());
        }
        return accountRepo.save(account);
    }

    public Account findAccountById(Long accountId) {
        return accountRepo.findById(accountId).orElse(null);
    }
}
