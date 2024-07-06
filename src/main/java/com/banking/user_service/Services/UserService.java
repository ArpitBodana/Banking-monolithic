package com.banking.user_service.Services;

import com.banking.user_service.Models.Account;
import com.banking.user_service.Models.User;
import com.banking.user_service.Models.UserPrinciple;
import com.banking.user_service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    @Autowired
    AccountService accountService;
    @Autowired
    UserRepository repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUserName(username);
        if(user==null){
            System.out.println("user not found");
            throw new UsernameNotFoundException("User not found !!");
        }
        return new UserPrinciple(user);
    }

    public User saveUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        Account account = new Account(0L,user.getUserName(),50000L,true);
        accountService.addNewAccount(account);
        return user;
    }
}
