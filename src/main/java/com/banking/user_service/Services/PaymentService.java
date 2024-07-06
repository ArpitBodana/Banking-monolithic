package com.banking.user_service.Services;


import com.banking.user_service.Models.*;
import com.banking.user_service.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PaymentService {

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionRepository transactionRepository;

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


    public String depositToAccount(DepositOrDebit deposit) {
        Account account =  accountService.getAccount(deposit.getAccountId());
        if(account.getId()!=null){
            Transactions transactions = new Transactions(0L,deposit.getAmount(), TransactionTypes.DEPOSIT,formatter.format(new Date()),0L,deposit.getAccountId());
            transactionRepository.save(transactions);
        }
        return accountService.depositToAccount(deposit.getAccountId(),deposit.getAmount());
    }

    public String debitFromAccount(DepositOrDebit debit) {
        Account account =  accountService.getAccount(debit.getAccountId());
        if(account.getId()!=null){
            Transactions transactions = new Transactions(0L,debit.getAmount(),TransactionTypes.DEBIT,formatter.format(new Date()),0L,debit.getAccountId());
            transactionRepository.save(transactions);
        }
        return accountService.debitFromAccount(debit.getAccountId(),debit.getAmount());
    }

    public String transferToAnotherAccount(TransferDetails transferDetails) {
        Account account =  accountService.getAccount(transferDetails.getSenderId());
        if(account.getId()!=null){
            Transactions transactions = new Transactions(0L,transferDetails.getTransferAmount(),TransactionTypes.TRANSFER,formatter.format(new Date()),transferDetails.getReceiverId(),transferDetails.getSenderId());
            transactionRepository.save(transactions);
        }

        return accountService.transferToAnotherAccount(transferDetails.getSenderId(),transferDetails.getReceiverId(),transferDetails.getTransferAmount());
    }



    public List<Transactions> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public List<Transactions> getMyTransactions(Long id){
        return transactionRepository.findByAccountId(id).stream().sorted((a,n)->n.getId()>a.getId()?1:-1).collect(Collectors.toList());
    }

}
