package com.banking.user_service.Config;


import com.banking.user_service.Models.Account;
import com.banking.user_service.Models.User;
import com.banking.user_service.Repository.AccountRepository;
import com.banking.user_service.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class UserConfig {
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    @Bean
    public CommandLineRunner userCommandLineRunner(UserRepository repo, AccountRepository accountRepository){
        return args -> {
            User user1 = new User(null,"Arpit","1234");
            User user2 = new User(null,"Chiku","1234");
            user1.setPassword(encoder.encode(user1.getPassword()));
            user2.setPassword(encoder.encode(user2.getPassword()));

            if(repo.findAll().isEmpty()){
                repo.saveAll(List.of(user1,user2));
                accountRepository.saveAll(List.of(new Account(0L,"Arpit",50000L,true),new Account(0L,"Chiku",50000L,true)));
            }
        };
    }
}
