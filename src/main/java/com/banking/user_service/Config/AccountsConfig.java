package com.banking.user_service.Config;


import com.banking.user_service.Models.Account;
import com.banking.user_service.Repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountsConfig {

    @Bean
    public CommandLineRunner accountConfig(AccountRepository repo){
        return args -> {
            Account accountAb= new Account(1L,"Arpit Bodana",2500000L,true);
            Account accountCb= new Account(2L,"Chiku Bodana",4500000L,true);

            if(repo.findAll().isEmpty()){
                repo.save(accountAb);
                repo.save(accountCb);
            }
        };
    }
}
