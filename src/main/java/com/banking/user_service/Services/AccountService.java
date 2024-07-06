package com.banking.user_service.Services;


import com.banking.user_service.Models.Account;
import com.banking.user_service.Repository.AccountRepository;
import com.banking.user_service.interfaces.IAccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccount(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElseGet(Account::new);
    }

    @Override
    public String addNewAccount(Account account) {
        accountRepository.save(account);
        return "Account Created!!";
    }

    @Override
    public String removeAccount(Long id) {
        Optional<Account> exits = accountRepository.findById(id);
        if (exits.isEmpty()) {
            return "Account not found!!";
        }
        accountRepository.deleteById(id);
        return "Account Removed !!";
    }

    @Transactional
    @Override
    public String updateAccount(Account account, Long id) {
        try {
            Account exits = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account NOt found!!"));
            exits.setAccountBalance(account.getAccountBalance());
            exits.setAccountHolder(account.getAccountHolder());
            accountRepository.save(exits);
            return "Account updated - " + exits.toString();
        }catch (Error e){
            return "Account Not Found!!";
        }

    }

    @Transactional
    @Override
    public String depositToAccount(Long accountId, Long depositAmount) {
        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isPresent()){
            account.get().setAccountBalance(account.get().getAccountBalance() + depositAmount);
            accountRepository.save(account.get());
            return "Amount "+depositAmount+" Deposited to account "+account.get().getId();
        }
        return "Unable to fetch account!!";
    }

    @Transactional
    @Override
    public String debitFromAccount(Long accountId, Long debitAmount) {
        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isPresent()){
            account.get().setAccountBalance(account.get().getAccountBalance() - debitAmount);
            accountRepository.save(account.get());
            return "Amount "+debitAmount+" debited from account "+account.get().getId();
        }
        return "Unable to fetch account!!";
    }

    @Transactional
    @Override
    public String transferToAnotherAccount(Long senderId, Long reciverId, Long transferAmount) {
        Optional<Account> senderAccount = accountRepository.findById(senderId);
        Optional<Account> receiverAccount = accountRepository.findById(reciverId);
        if(senderAccount.get().getId().equals(receiverAccount.get().getId())){
            return "You can't transfer data to your same account.";
        }
        if(senderAccount.isEmpty()||receiverAccount.isEmpty()){
            return "Unable to fetch the account details";
        }
        if(senderAccount.get().getAccountBalance()<transferAmount){
            return "Insufficient balance for these transaction";
        }
        if(!receiverAccount.get().getIsActive()){
            return "Receiver is account is not active , transaction cannot be proceed.";
        }
        senderAccount.get().setAccountBalance(senderAccount.get().getAccountBalance()-transferAmount);
        accountRepository.save(senderAccount.get());
        receiverAccount.get().setAccountBalance(receiverAccount.get().getAccountBalance()+transferAmount);
        accountRepository.save(receiverAccount.get());
        return "Amount "+transferAmount+" is transferred to account "+reciverId;
    }
}
